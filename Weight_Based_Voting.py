import numpy as np
import os
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.image import load_img, img_to_array


from tensorflow.keras.applications.inception_v3 import preprocess_input as preprocess_input_inception
from tensorflow.keras.applications.mobilenet_v2 import preprocess_input as preprocess_input_mobilenet
from tensorflow.keras.applications.efficientnet import preprocess_input as preprocess_input_effi
from tensorflow.keras.applications.resnet import preprocess_input as preprocess_input_res


from tensorflow.keras.utils import to_categorical
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import GlobalAveragePooling2D, Dense, Dropout
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import ModelCheckpoint, EarlyStopping, ReduceLROnPlateau
import matplotlib.pyplot as plt
from tensorflow.keras.models import load_model
from sklearn.metrics import accuracy_score, f1_score, precision_score, recall_score, roc_curve, auc, precision_recall_curve, average_precision_score, confusion_matrix
import matplotlib.pyplot as plt

# Define paths to the models you want to use for majority voting

model1 = load_model(r'best_model_MobileNet.h5')
model2 = load_model(r'best_model_GoogleNet.h5')
model3 = load_model(r'best_model_EfficientNet.h5')
model4 = load_model(r'best_model_ResNet.h5')


test_dir = r'testing directory'

def load_and_preprocess_image_Mobile(file_path):
    img = load_img(file_path, target_size=(224, 224))
    img_array = img_to_array(img)
    img_array = preprocess_input_mobilenet(img_array)
    return img_array

def load_and_preprocess_image_GoogleNet(file_path):
    img = load_img(file_path, target_size=(224, 224))
    img_array = img_to_array(img)
    img_array = preprocess_input_inception(img_array)
    return img_array

def load_and_preprocess_image_Efficient(file_path):
    img = load_img(file_path, target_size=(224, 224))
    img_array = img_to_array(img)
    img_array = preprocess_input_effi(img_array)
    return img_array

def load_and_preprocess_image_Resnet(file_path):
    img = load_img(file_path, target_size=(224, 224))
    img_array = img_to_array(img)
    img_array = preprocess_input_res(img_array)
    return img_array



def load_data_from_directory(directory,m):
    data = []
    labels = []
    class_indices = {}
    for idx, class_name in enumerate(sorted(os.listdir(directory))):
        class_indices[class_name] = idx
        class_dir = os.path.join(directory, class_name)
        for filename in os.listdir(class_dir):
            if filename.lower().endswith((".jpg", ".jpeg", ".png")):
                img_path = os.path.join(class_dir, filename)
                if m == 1:
                    img_array = load_and_preprocess_image_Mobile(img_path)
                elif m == 2:
                    img_array = load_and_preprocess_image_GoogleNet(img_path)
                elif m == 3:
                    img_array = load_and_preprocess_image_Efficient(img_path)
                else:
                    img_array = load_and_preprocess_image_Resnet(img_path)

                data.append(img_array)
                labels.append(idx)
    return np.array(data), np.array(labels), class_indices



test_data, test_labels, class_indices = load_data_from_directory(test_dir,1)
num_classes = len(class_indices)
predictions = model1.predict(test_data)
predicted_classes1 = np.argmax(predictions, axis=1)
accuracy1 = accuracy_score(test_labels,predicted_classes1)
print("MobileNet_Predictions")
print(predicted_classes1)


test_data, test_labels, class_indices = load_data_from_directory(test_dir,2)
predictions = model2.predict(test_data)
predicted_classes2 = np.argmax(predictions, axis=1)
accuracy2 = accuracy_score(test_labels,predicted_classes2)
print("GoogleNet_Predictions")
print(predicted_classes2)

test_data, test_labels, class_indices = load_data_from_directory(test_dir,3)
predictions = model3.predict(test_data)
predicted_classes3 = np.argmax(predictions, axis=1)
accuracy3 = accuracy_score(test_labels,predicted_classes3)
print("EfficientNet_Predictions")
print(predicted_classes3)

test_data, test_labels, class_indices = load_data_from_directory(test_dir,4)
predictions = model4.predict(test_data)
predicted_classes4 = np.argmax(predictions, axis=1)
accuracy4 = accuracy_score(test_labels,predicted_classes4)
print("ResNet_Predictions")
print(predicted_classes4)


class_array = [0] * 10

accuracies = [accuracy1, accuracy2, accuracy3, accuracy4]
print(accuracies)

final_predictions = []
for i in range(len(predicted_classes1)):
    class_array = [0] * 10
    class_array[predicted_classes1[i]] += accuracy1
    class_array[predicted_classes2[i]] += accuracy2
    class_array[predicted_classes3[i]] += accuracy3
    class_array[predicted_classes4[i]] += accuracy4
    final_predictions.append(class_array.index(max(class_array)))



#final_predictions = np.column_stack(final_predictions)
print("Final Predictions after weight based voting")
print(final_predictions)

accuracy = accuracy_score(test_labels, final_predictions)
f1 = f1_score(test_labels, final_predictions, average='weighted')
precision = precision_score(test_labels, final_predictions, average='weighted')
recall = recall_score(test_labels, final_predictions, average='weighted')

print("Accuracy:", accuracy)
print("F1 Score:", f1)
print("Precision:", precision)
print("Recall:", recall)



# Plot the confusion matrix
import seaborn as sns
import pandas as pd
conf_matrix = confusion_matrix(test_labels, final_predictions)
print("Confusion Matrix:")
print(conf_matrix)

import seaborn as sns
import matplotlib.pyplot as plt

plt.figure(figsize=(10, 7))
sns.heatmap(conf_matrix, annot=True, fmt='d', cmap="Blues", xticklabels=class_indices.keys(), yticklabels=class_indices.keys())
plt.xlabel('Predicted')
plt.ylabel('True')
plt.title('Confusion Matrix')
plt.show()
