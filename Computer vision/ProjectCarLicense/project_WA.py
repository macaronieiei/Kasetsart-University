import cv2
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt
import os

def binarize_image(image, threshold=150):
    grayscale_image = image.convert('L')
    gray_array = np.array(grayscale_image)
    
    # ใช้ Gaussian Blur
    blurred_image = cv2.GaussianBlur(gray_array, (5, 5), 0)
    
    enhanced_array = np.clip(blurred_image * 1.5, 0, 255).astype(np.uint8)
    binary_array = np.array(enhanced_array) > threshold
    binary_image = Image.fromarray(binary_array.astype(np.uint8) * 255)
    return binary_image

def vertical_projection(binary_image):
    img_array = np.array(binary_image)
    projection = np.sum(img_array == 0, axis=0)
    return projection

def segment_characters_modified(projection, min_width=5, threshold=5):
    segments = []
    in_character = False
    start_index = 0
    for i, value in enumerate(projection):
        if value > threshold and not in_character:
            in_character = True
            start_index = i
        elif (value <= threshold and in_character) or (i == len(projection)-1 and in_character):
            in_character = False
            end_index = i if value <= threshold else i+1
            if end_index - start_index >= min_width:
                segments.append((start_index, end_index))
    return segments

def extract_characters(binary_image, segments):
    img_array = np.array(binary_image)
    characters = []
    for start, end in segments:
        if start >= 0 and end <= img_array.shape[1]:
            char_img = img_array[:, start:end]
            rows = np.any(char_img == 0, axis=1)
            char_img = char_img[rows]
            characters.append(char_img)
    return characters

def get_character_pattern(image_path, target_size=(40, 40)):
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
    resized_image = cv2.resize(image, target_size, interpolation=cv2.INTER_AREA)
    _, binary_image = cv2.threshold(resized_image, 127, 255, cv2.THRESH_BINARY_INV)
    vertical_projection = np.sum(binary_image, axis=0)
    pattern = vertical_projection / max(vertical_projection)
    return pattern

def load_character_patterns():
    char_images = {
        '0': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\0.jpg',
        '1': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\1.jpg',
        '2': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\2.jpg',
        '3': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\3.jpg',
        '4': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\4.jpg',
        '5': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\5.jpg',
        '6': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\6.jpg',
        '7': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\7.jpg',
        '8': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\8.jpg',
        '9': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Number\\9.jpg',

        'ก': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\01.jpg',
        'ข': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\02.jpg',
        'ฃ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\03.jpg',
        'ค': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\04.jpg',
        'ฅ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\05.jpg',
        'ฆ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\06.jpg',
        'ง': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\07.jpg',
        'จ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\08.jpg',
        'ฉ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\09.jpg',
        'ช': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\10.jpg',
        'ซ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\11.jpg',
        'ฌ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\12.jpg',
        'ญ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\13.jpg',
        'ฎ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\14.jpg',
        'ฏ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\15.jpg',
        'ฐ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\16.jpg',
        'ฑ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\17.jpg',
        'ฒ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\18.jpg',
        'ณ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\19.jpg',
        'ด': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\20.jpg',
        'ต': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\21.jpg',
        'ถ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\22.jpg',
        'ท': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\23.jpg',
        'ธ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\24.jpg',
        'น': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\25.jpg',
        'บ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\26.jpg',
        'ป': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\27.jpg',
        'ผ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\28.jpg',
        'ฝ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\29.jpg',
        'พ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\30.jpg',
        'ฟ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\31.jpg',
        'ภ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\32.jpg',
        'ม': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\33.jpg',
        'ย': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\34.jpg',
        'ร': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\35.jpg',
        'ล': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\36.jpg',
        'ว': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\37.jpg',
        'ศ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\38.jpg',
        'ษ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\39.jpg',
        'ส': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\40.jpg',
        'ห': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\41.jpg',
        'ฬ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\42.jpg',
        'อ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\43.jpg',
        'ฮ': 'D:\\KU KPS\\Comvision\\License Plate Recognition\\Letter\\44.jpg'

    }
    patterns = {}
    for char, path in char_images.items():
        patterns[char] = get_character_pattern(path)
    return patterns

def recognize_characters(projection, segments, char_patterns):
    results = []
    threshold = 0.5  # Threshold for matching
    for start, end in segments:
        char_projection = projection[start:end]
        if len(char_projection) < 2:
            continue
            
        # Normalize and smooth the character projection
        normalized_projection = np.interp(
            np.linspace(0, 1, 40), 
            np.linspace(0, 1, len(char_projection)), 
            char_projection
        )
        normalized_projection = normalized_projection / max(normalized_projection)

        # Store similarities
        similarities = {}
        for char, pattern in char_patterns.items():
            similarity = np.sum(np.abs(normalized_projection - pattern))
            similarities[char] = similarity
        
        # Get the best match
        best_match = min(similarities, key=similarities.get)

        # Check if best match is within the threshold
        if similarities[best_match] < threshold:
            results.append(best_match)
        else:
            # Estimate close matches
            close_matches = sorted(similarities.items(), key=lambda x: x[1])
            estimated_match = close_matches[0][0]  # Best guess
            results.append(estimated_match)  # Add the estimated match

    return results

def process_image(image_path):
    # Set Tahoma as the default font for plots
    plt.rcParams['font.family'] = 'Tahoma'

    char_patterns = load_character_patterns()
    
    normalized_path = os.path.normpath(image_path)
    if not os.path.exists(normalized_path):
        print(f"Image file not found: {normalized_path}")
        return
    
    image = Image.open(normalized_path)
    new_size = (633, 131)
    image = image.resize(new_size)

    binary_image = binarize_image(image, threshold=100)
    projection = vertical_projection(binary_image)
    plt.figure(figsize=(12, 4))
    plt.plot(projection)
    plt.title("Vertical Projection")
    plt.xlabel('Column Index')
    plt.ylabel('Black Pixel Count')
    plt.grid(True)
    plt.show()

    segments = segment_characters_modified(projection)
    characters = extract_characters(binary_image, segments)
    recognized = recognize_characters(projection, segments, char_patterns)
    result_text = ''.join(recognized).strip()
    print("Recognized text:", result_text)
    
    if len(characters) > 0:
        cols = min(5, len(characters))
        rows = (len(characters) - 1) // cols + 1
        plt.figure(figsize=(cols * 2, rows * 2))
        for i, char_img in enumerate(characters):
            plt.subplot(rows, cols, i + 1)
            plt.imshow(char_img, cmap='gray')
            plt.title(f'Char: {recognized[i] if i < len(recognized) else ""}')
            plt.axis('off')
        plt.tight_layout()
        plt.show()
    else:
        print("No characters were segmented.")


if __name__ == "__main__":
    test01_path = "D:\\KU KPS\\Comvision\\License Plate Recognition\\Test\\test01.jpg"
    process_image(test01_path)
    test02_path = "D:\\KU KPS\\Comvision\\License Plate Recognition\\Test\\test02.jpg"
    process_image(test02_path)
    test03_path = "D:\\KU KPS\\Comvision\\License Plate Recognition\\Test\\test03.jpg"
    process_image(test03_path)
    test04_path = "D:\\KU KPS\\Comvision\\License Plate Recognition\\Test\\test04.jpg"
    process_image(test04_path)
    
