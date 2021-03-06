import tensorflow as tf
from tqdm import tqdm
import numpy as np


def load_image(image_path):
    img = tf.io.read_file(image_path)
    img = tf.image.decode_jpeg(img, channels=3)
    img = tf.image.resize(img, (299, 299))
    img = tf.keras.applications.inception_v3.preprocess_input(img)
    return img, image_path

def get_img_feature_extract_model():
  image_model = tf.keras.applications.InceptionV3(include_top=False, weights='imagenet')
  new_input = image_model.input
  hidden_layer = image_model.layers[-1].output
  image_features_extract_model = tf.keras.Model(new_input, hidden_layer)
  return image_features_extract_model


def make_img_np(img_name_vector):
  image_features_extract_model = get_img_feature_extract_model()

  # Get unique images
  encode_train = sorted(set(img_name_vector))

  # Feel free to change batch_size according to your system configuration
  image_dataset = tf.data.Dataset.from_tensor_slices(encode_train)
  image_dataset = image_dataset.map(
    load_image, num_parallel_calls=tf.data.AUTOTUNE).batch(16)

  for img, path in tqdm(image_dataset):
    batch_features = image_features_extract_model(img)
    batch_features = tf.reshape(batch_features,
                                (batch_features.shape[0], -1, batch_features.shape[3]))

    for bf, p in zip(batch_features, path):
      path_of_feature = p.numpy().decode("utf-8")
      np.save(path_of_feature, bf.numpy())