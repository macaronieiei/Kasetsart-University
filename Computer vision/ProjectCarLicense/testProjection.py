import cv2
import numpy as np

def get_character_pattern(image_path, target_size=(40, 40)):
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
    
    # Resize ให้ภาพมีขนาดเท่ากันทุกภาพ
    resized_image = cv2.resize(image, target_size, interpolation=cv2.INTER_AREA)
    
    # แปลงภาพเป็น binary image
    _, binary_image = cv2.threshold(resized_image, 127, 255, cv2.THRESH_BINARY_INV)

    # คำนวณ vertical projection
    vertical_projection = np.sum(binary_image, axis=0)

    # Normalize pattern ให้ค่าอยู่ระหว่าง 0-1
    pattern = vertical_projection / max(vertical_projection)
    
    return pattern

pattern = get_character_pattern("D:\\pythonWa\\Number\\0.jpg")
print("Pattern ของ เลข 0:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\1.jpg")
print("Pattern ของ เลข 1:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\2.jpg")
print("Pattern ของ เลข 2:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\3.jpg")
print("Pattern ของ เลข 3:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\4.jpg")
print("Pattern ของ เลข 4:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\5.jpg")
print("Pattern ของ เลข 5:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\6.jpg")
print("Pattern ของ เลข 6:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\7.jpg")
print("Pattern ของ เลข 7:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\8.jpg")
print("Pattern ของ เลข 8:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\Number\\9.jpg")
print("Pattern ของ เลข 9:", pattern)

pattern = get_character_pattern("D:\\pythonWa\\11.jpg")
print("Pattern ของ เลข ก:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\22.jpg")
print("Pattern ของ เลข ข:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\33.jpg")
print("Pattern ของ เลข ค:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\44.jpg")
print("Pattern ของ เลข ฆ:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\55.jpg")
print("Pattern ของ เลข ง:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\66.jpg")
print("Pattern ของ เลข จ:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\77.jpg")
print("Pattern ของ เลข ฉ:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\88.jpg")
print("Pattern ของ เลข ช:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\99.png")
print("Pattern ของ เลข ฌ:", pattern)
pattern = get_character_pattern("D:\\pythonWa\\1010.jpg")
print("Pattern ของ เลข ญ:", pattern)



