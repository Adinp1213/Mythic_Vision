import numpy as np
import os
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.image import load_img, img_to_array
from tensorflow.keras.applications.efficientnet import preprocess_input # replace the model with the model used
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.applications import EfficientNetB0 # similarly here
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import GlobalAveragePooling2D, Dense, Dropout
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import ModelCheckpoint, EarlyStopping, ReduceLROnPlateau
import matplotlib.pyplot as plt
from tensorflow.keras.models import load_model


from sklearn.metrics import classification_report, confusion_matrix, f1_score, precision_score, recall_score


test_dir = r'testing directory'

model = load_model(r'model.h5') # put model here

def load_and_preprocess_image(file_path):
    img = load_img(file_path, target_size=(224, 224))
    img_array = img_to_array(img)
    img_array = preprocess_input(img_array)
    return img_array

def load_data_from_directory(directory):
    data = []
    labels = []
    class_indices = {}
    for idx, class_name in enumerate(sorted(os.listdir(directory))):
        class_indices[class_name] = idx
        class_dir = os.path.join(directory, class_name)
        for filename in os.listdir(class_dir):
            if filename.lower().endswith((".jpg", ".jpeg", ".png")):  # Supporting multiple image formats
                img_path = os.path.join(class_dir, filename)
                img_array = load_and_preprocess_image(img_path)
                data.append(img_array)
                labels.append(idx)
    return np.array(data), np.array(labels), class_indices

test_data, test_labels, class_indices = load_data_from_directory(test_dir)
num_classes = len(class_indices)
predictions = model.predict(test_data)
predicted_classes = np.argmax(predictions, axis=1)
test_labels = to_categorical(test_labels, num_classes)
from sklearn.metrics import confusion_matrix
import seaborn as sns

# Evaluate the model on test data
test_loss, test_accuracy = model.evaluate(test_data, test_labels)
print(f"Test Loss: {test_loss:.4f}")
print(f"Test Accuracy: {test_accuracy:.4f}")


# Calculate F1 score, precision, and recall
f1 = f1_score(np.argmax(test_labels, axis=1), predicted_classes, average='weighted') * 100
precision = precision_score(np.argmax(test_labels, axis=1), predicted_classes, average='weighted') * 100
recall = recall_score(np.argmax(test_labels, axis=1), predicted_classes, average='weighted') * 100


print(f"Weighted F1 Score: {f1:.2f}%")
print(f"Weighted Precision: {precision:.2f}%")
print(f"Weighted Recall: {recall:.2f}%")


conf_matrix = confusion_matrix(np.argmax(test_labels, axis=1), predicted_classes)
plt.figure(figsize=(8, 6))
sns.heatmap(conf_matrix, annot=True, fmt='d', cmap='Blues', xticklabels=class_indices.keys(), yticklabels=class_indices.keys())
plt.title('Confusion Matrix')
plt.xlabel('Predicted Label')
plt.ylabel('True Label')
plt.show()
