import numpy as np
import os
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.image import load_img, img_to_array
from tensorflow.keras.applications import InceptionV3
from tensorflow.keras.applications.inception_v3 import preprocess_input as inception_preprocess_input
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import GlobalAveragePooling2D, Dense, Dropout
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import ModelCheckpoint, EarlyStopping, ReduceLROnPlateau
import matplotlib.pyplot as plt


train_dir = r'training'
validation_dir = r'validation'
test_dir = r'testing'

datagen = ImageDataGenerator(
    rotation_range=20,
    width_shift_range=0.2,
    height_shift_range=0.2,
    zoom_range=0.2,
    fill_mode='nearest'
)

def load_and_preprocess_image(file_path):
    img = load_img(file_path, target_size=(224, 224))
    img_array = img_to_array(img)
    img_array = inception_preprocess_input(img_array)
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


train_data, train_labels, class_indices = load_data_from_directory(train_dir)
validation_data, validation_labels, _ = load_data_from_directory(validation_dir)
test_data, test_labels, _ = load_data_from_directory(test_dir)

# Convert labels to categorical format
num_classes = len(class_indices)
train_labels = to_categorical(train_labels, num_classes)
validation_labels = to_categorical(validation_labels, num_classes)
test_labels = to_categorical(test_labels, num_classes)


base_model = InceptionV3(input_shape=(224, 224, 3), include_top=False, weights='imagenet')
model = Sequential([
    base_model,
    GlobalAveragePooling2D(),
    Dense(512, activation='relu'),
    Dropout(0.5),
    Dense(num_classes, activation='softmax')
])



# Compile the model
model.compile(optimizer=Adam(learning_rate=0.0001), loss='categorical_crossentropy', metrics=['accuracy'])

for layer in base_model.layers:
    layer.trainable = False  # Freeze all layers

# Callbacks
checkpoint = ModelCheckpoint('best_model_GoogleNet.h5', monitor='val_loss', save_best_only=True, verbose=1)
early_stopping = EarlyStopping(monitor='val_loss', patience=10, restore_best_weights=True)
reduce_lr = ReduceLROnPlateau(monitor='val_loss', factor=0.2, patience=4, min_lr=1e-6)

# Train the model
batch_size = 32
history = model.fit(
    datagen.flow(train_data, train_labels, batch_size=batch_size),
    epochs=50,
    validation_data=(validation_data, validation_labels),
    callbacks=[checkpoint, early_stopping, reduce_lr]
)


# training and validation accuracy plot
plt.plot(history.history['accuracy'], label='Training Accuracy')
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.title('Training and Validation Accuracy')
plt.legend()
plt.savefig('accuracy_plot.png')
plt.close()


# training and validation loss plot
plt.plot(history.history['loss'], label='Training Loss')
plt.plot(history.history['val_loss'], label='Validation Loss')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.title('Training and Validation Loss')
plt.legend()
plt.savefig('loss_plot.png')
plt.close()



results_folder = r'Results'
if not os.path.exists(results_folder):
    os.makedirs(results_folder)
os.rename('accuracy_plot.png', os.path.join(results_folder, 'accuracy_plot.png'))
os.rename('loss_plot.png', os.path.join(results_folder, 'loss_plot.png'))
