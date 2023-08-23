import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
import os
from PIL import Image, ImageOps  # Add this line to import ImageOps module

# Update the directory paths using raw strings or escaped backslashes
input_dir = r'input directory path'
output_dir = r'output directory path'

if not os.path.exists(output_dir):
    os.makedirs(output_dir)

# Data augmentation
num_augmented_images = 5  # Number of augmented images per input image

# Define data augmentation parameters
data_gen = ImageDataGenerator(
    rescale=1.0 / 255,
    rotation_range=20,
    width_shift_range=0.2,
    height_shift_range=0.2,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True,
    fill_mode='nearest'
)

# Load original images
image_files = [os.path.join(input_dir, filename) for filename in os.listdir(input_dir) if
               filename.endswith(".jpg") or filename.endswith(".png") or filename.endswith(".jpeg")]

for img_path in image_files:
    img = tf.keras.preprocessing.image.load_img(img_path, target_size=(224, 224))
    img_array = tf.keras.preprocessing.image.img_to_array(img)
    img_array = img_array.reshape((1,) + img_array.shape)  # Add batch dimension

    # Generate augmented images using the flow method
    img_name = os.path.basename(img_path).split('.')[0]  # Extract image name without extension
    generator = data_gen.flow(img_array, batch_size=1)

    for i in range(num_augmented_images):
        batch = generator.next()
        augmented_img = batch[0]

        # Calculate padding
        width_diff = 224 - augmented_img.shape[1]
        height_diff = 224 - augmented_img.shape[0]
        left_padding = width_diff // 2
        top_padding = height_diff // 2

        # Apply padding with black color
        padded_img = ImageOps.expand(Image.fromarray((augmented_img * 255).astype('uint8')),
                                     (left_padding, top_padding, width_diff - left_padding, height_diff - top_padding),
                                     fill='black')

        # Save the padded augmented image
        augmented_img_path = os.path.join(output_dir, f"{img_name}_aug_{i}.jpg")
        padded_img.save(augmented_img_path)

print(f"{len(image_files) * num_augmented_images} augmented images with proper padding generated and saved.")
