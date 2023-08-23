from PIL import Image
import os


def resize_images(source_dir, dest_dir, target_size):
    if not os.path.exists(dest_dir):
        os.makedirs(dest_dir)

    image_files = [f for f in os.listdir(source_dir) if f.lower().endswith(('.jpg', '.png', '.jpeg'))]

    for image_file in image_files:
        image_path = os.path.join(source_dir, image_file)
        dest_path = os.path.join(dest_dir, image_file)

        # Open the image and calculate the aspect ratio
        img = Image.open(image_path)
        width, height = img.size
        aspect_ratio = width / height

        # Resize the image while maintaining the aspect ratio
        new_width = target_size[0]
        new_height = int(new_width / aspect_ratio)
        resized_img = img.resize((new_width, new_height), Image.LANCZOS )

        # Create a blank canvas of the target size and paste the resized image
        canvas = Image.new("RGB", target_size, "black")
        paste_x = 0
        paste_y = (target_size[1] - new_height) // 2
        canvas.paste(resized_img, (paste_x, paste_y))

        # Save the resized image with proper aspect ratio
        canvas.save(dest_path)


# Specify paths and target size
source_directory = r'source directory location'
destination_directory = r'destination directory location'
target_size = (224, 224)  # The desired target size

# Resize all images in the source directory and save them to the destination directory
resize_images(source_directory, destination_directory, target_size)
