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

def segment_characters_modified(projection, min_width=2, threshold=1):
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
        '0': 'D:\\pythonWa\\number\\0.jpg',
        '1': 'D:\\pythonWa\\number\\1.jpg',
        '2': 'D:\\pythonWa\\number\\2.jpg',
        '3': 'D:\\pythonWa\\number\\3.jpg',
        '4': 'D:\\pythonWa\\number\\4.jpg',
        '5': 'D:\\pythonWa\\number\\5.jpg',
        '6': 'D:\\pythonWa\\number\\6.jpg',
        '7': 'D:\\pythonWa\\number\\7.jpg',
        '8': 'D:\\pythonWa\\number\\8.jpg',
        '9': 'D:\\pythonWa\\number\\9.jpg',

        'ก': 'D:\\pythonWa\\Letter\\01.jpg',
        'ข': 'D:\\pythonWa\\Letter\\02.jpg',
        'ฃ': 'D:\\pythonWa\\Letter\\03.jpg',
        'ค': 'D:\\pythonWa\\Letter\\04.jpg',
        'ฅ': 'D:\\pythonWa\\Letter\\05.jpg',
        'ฆ': 'D:\\pythonWa\\Letter\\06.jpg',
        'ง': 'D:\\pythonWa\\Letter\\07.jpg',
        'จ': 'D:\\pythonWa\\Letter\\08.jpg',
        'ฉ': 'D:\\pythonWa\\Letter\\09.jpg',
        'ช': 'D:\\pythonWa\\Letter\\10.jpg',
        'ซ': 'D:\\pythonWa\\Letter\\11.jpg',
        'ฌ': 'D:\\pythonWa\\Letter\\12.jpg',
        'ญ': 'D:\\pythonWa\\Letter\\13.jpg',
        'ฎ': 'D:\\pythonWa\\Letter\\14.jpg',
        'ฏ': 'D:\\pythonWa\\Letter\\15.jpg',
        'ฐ': 'D:\\pythonWa\\Letter\\16.jpg',
        'ฑ': 'D:\\pythonWa\\Letter\\17.jpg',
        'ฒ': 'D:\\pythonWa\\Letter\\18.jpg',
        'ณ': 'D:\\pythonWa\\Letter\\19.jpg',
        'ด': 'D:\\pythonWa\\Letter\\20.jpg',
        'ต': 'D:\\pythonWa\\Letter\\21.jpg',
        'ถ': 'D:\\pythonWa\\Letter\\22.jpg',
        'ท': 'D:\\pythonWa\\Letter\\23.jpg',
        'ธ': 'D:\\pythonWa\\Letter\\24.jpg',
        'น': 'D:\\pythonWa\\Letter\\25.jpg',
        'บ': 'D:\\pythonWa\\Letter\\26.jpg',
        'ป': 'D:\\pythonWa\\Letter\\27.jpg',
        'ผ': 'D:\\pythonWa\\Letter\\28.jpg',
        'ฝ': 'D:\\pythonWa\\Letter\\29.jpg',
        'พ': 'D:\\pythonWa\\Letter\\30.jpg',
        'ฟ': 'D:\\pythonWa\\Letter\\31.jpg',
        'ภ': 'D:\\pythonWa\\Letter\\32.jpg',
        'ม': 'D:\\pythonWa\\Letter\\33.jpg',
        'ย': 'D:\\pythonWa\\Letter\\34.jpg',
        'ร': 'D:\\pythonWa\\Letter\\35.jpg',
        'ล': 'D:\\pythonWa\\Letter\\36.jpg',
        'ว': 'D:\\pythonWa\\Letter\\37.jpg',
        'ศ': 'D:\\pythonWa\\Letter\\38.jpg',
        'ษ': 'D:\\pythonWa\\Letter\\39.jpg',
        'ส': 'D:\\pythonWa\\Letter\\40.jpg',
        'ห': 'D:\\pythonWa\\Letter\\41.jpg',
        'ฬ': 'D:\\pythonWa\\Letter\\42.jpg',
        'อ': 'D:\\pythonWa\\Letter\\43.jpg',
        'ฮ': 'D:\\pythonWa\\Letter\\44.jpg'

    }
    patterns = {}
    for char, path in char_images.items():
        patterns[char] = get_character_pattern(path)
    return patterns

def recognize_characters(projection, segments, char_patterns):
    results = []
    threshold = 0.3  # Threshold for matching
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
    test01_path = "D:\\pythonWa\\test01.jpg"
    process_image(test01_path)
    test02_path = "D:\\pythonWa\\test02.png"
    process_image(test02_path)
    test03_path = "D:\\pythonWa\\test03.jpg"
    process_image(test03_path)
    test04_path = "D:\\pythonWa\\test04.jpg"
    process_image(test04_path)
    