import numpy as np
from tensorflow.keras.preprocessing import image
from tensorflow.keras.applications.mobilenet_v2 import preprocess_input # replace model used
from tensorflow.keras.models import load_model

# Load your trained MobileNetV2 model
model = load_model('model.h5') # put model used

# Input path to your image file
image_path = r'image path'  # Replace with the path to your image

# Load and preprocess the image
img = image.load_img(image_path, target_size=(224, 224))
img = image.img_to_array(img)
img = np.expand_dims(img, axis=0)
img = preprocess_input(img)

# Make predictions
predictions = model.predict(img)

# Get the predicted class label
predicted_class = np.argmax(predictions)

# Define class labels (adjust as needed)
class_labels = [
    "Balaji",
    "Durga Maa",
    "Ganesha",
    "Hanuman",
    "Kali Maa",
    "Khatu Shyam",
    "Krishna",
    "Sai Baba",
    "Saraswati",
    "Shiva"
]

# Display the predicted class label
predicted_label = class_labels[predicted_class]
print("Predicted Class:", predicted_label)
