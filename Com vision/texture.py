import cv2
import numpy as np

# Read and convert the texture image to grayscale
red_apple_texture = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\Texture\red_apple_texture.jpg")
red_apple_texture_gray = cv2.cvtColor(red_apple_texture, cv2.COLOR_BGR2GRAY)

# Calculate mean gray value normalized to [0,1]
def calMeanGray(img):
    mean = np.mean(img)
    normalize = mean / 255
    return normalize

print("MeanTexture1 : ", calMeanGray(red_apple_texture_gray))

# Calculate Fedgeness based on Sobel edge detection and threshold
def calFEdgeness(img, threshold):
    sobel = cv2.Sobel(img, cv2.CV_64F, 1, 1, ksize=5)
    sobelABS = np.abs(sobel)
    totalPixels = sobel.size
    totalEdgePixels = np.sum(sobelABS > threshold)
    fedgeness = totalEdgePixels / totalPixels
    return fedgeness

print("Fedgeness1 : ", calFEdgeness(red_apple_texture_gray, 90))

# Create texture histogram consisting of normalized mean gray value and Fedgeness
def calTextureHis(img):
    texture_histogram = np.array([calMeanGray(img), calFEdgeness(img,80)])
    return texture_histogram

print("TextureHis1 : ", calTextureHis(red_apple_texture_gray))

# Calculate L1 distance between two texture histograms
def calL1Dist(his1, his2):
    return np.sum(np.abs(his1 - his2))

# Apply texture overlay based on L1 distance between texture and sub-image
def textureOverlay(img, texture, threshold):
    imgOut = np.copy(img)
    hisTexture = calTextureHis(texture)
    windowSize = 21
    r, c = img.shape
    half_window = windowSize // 2
    for i in range(r - windowSize + 1):
        for j in range(c - windowSize + 1):
            subImg = img[i:i + windowSize, j:j + windowSize]
            hisSubImg = calTextureHis(subImg)
            if calL1Dist(hisTexture, hisSubImg) < threshold:
                imgOut[i + half_window, j + half_window] = 255
    return imgOut

# Read other apple images in grayscale
red_apple1 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\Texture\red_apple1.png", cv2.IMREAD_GRAYSCALE)
red_apple2 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\Texture\red_apple2.png", cv2.IMREAD_GRAYSCALE)
red_apple3 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\Texture\red_apple3.jpg", cv2.IMREAD_GRAYSCALE)
red_apple4 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\Texture\red_apple4.jpg", cv2.IMREAD_GRAYSCALE)

def resizeImg(img) :
    # กำหนดความกว้างหรือความสูงใหม่
    new_width = 300  # กำหนดความกว้างใหม่
    height, width = img.shape[:2]

    # คำนวณอัตราส่วนการปรับขนาด
    aspect_ratio = width / height

    # คำนวณความสูงใหม่เพื่อคงสัดส่วน
    new_height = int(new_width / aspect_ratio)

    # ปรับขนาดรูปภาพ
    resized_image = cv2.resize(img, (new_width, new_height))

    # คำนวณอัตราส่วนการปรับขนาด
    spect_ratio = width / height

    # คำนวณความสูงใหม่เพื่อคงสัดส่วน
    new_height = int(new_width / aspect_ratio)

    # ปรับขนาดรูปภาพ
    resized_image = cv2.resize(img, (new_width, new_height))

    return resized_image

red_apple1 = resizeImg(red_apple1)
red_apple2 = resizeImg(red_apple2)
red_apple3 = resizeImg(red_apple3)
red_apple4 = resizeImg(red_apple4)

# # Test red_apple1
# result = textureOverlay(red_apple1, red_apple_texture_gray, 0.21)
# cv2.imshow('red_apple1_0.2', result)

# # Test red_apple2
# result = textureOverlay(red_apple2, red_apple_texture_gray, 0.2)
# cv2.imshow('red_apple2_0.2', result)

# # Test red_apple3
# result = textureOverlay(red_apple3, red_apple_texture_gray, 0.18)
# cv2.imshow('red_apple3_0.18', result)

# Test red_apple4
result = textureOverlay(red_apple4, red_apple_texture_gray, 0.20)
cv2.imshow('red_apple4_0.2', result)

cv2.waitKey(0)
cv2.destroyAllWindows()