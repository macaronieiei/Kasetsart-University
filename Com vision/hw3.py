import cv2
import numpy as np
img1=cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\texture1.png", cv2.IMREAD_GRAYSCALE)
img2=cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\texture2.png", cv2.IMREAD_GRAYSCALE)
img3=cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\texture3.png", cv2.IMREAD_GRAYSCALE)

def calMeanGray(img):
    return  np.average(img)/255


def calFEdgeness(img, threshold):
    sobel_magnitude = cv2.Sobel(src=img,ddepth=cv2.CV_64F,dx=1,dy=1,ksize=5)
    sobel_magnitude = np.abs(sobel_magnitude)
    count_edge = 0
    count_px = 0
    # Iterate through each pixel in the image
    rows, cols = sobel_magnitude.shape
    for i in range(rows):
        for j in range(cols):
            count_px += 1
            if sobel_magnitude[i, j] > threshold:
                count_edge += 1
    return (float)(count_edge) / count_px


def calTextureHis(img):
    textureHis = np.array([])
    textureHis = np.append(calMeanGray(img),calFEdgeness(img,100))
    return textureHis

def calL1Dist(his1,his2):
    return np.abs(his1[0]-his2[0])+np.abs(his1[1]-his2[1])

def textureOverlay(img , texture , threshold):
    imgOut = np.copy(img)
    hisTexture = calTextureHis(texture)
    r,c = img.shape
    winDowSize = 21
    for i in range( r-winDowSize +1):
        for j in range(c-winDowSize+1):
            subimg = img[i:i+winDowSize,j:j+winDowSize]
            hisSubimg = calTextureHis(subimg)
            if calL1Dist(hisTexture,hisSubimg)< threshold:
                imgOut[i+winDowSize//2][j+winDowSize//2] = 255
    return  imgOut

print(calMeanGray(img1))
print(calMeanGray(img2))
print(calMeanGray(img3))
print('-------------------------------------- end mean')
print(calFEdgeness(img1,100))
print(calFEdgeness(img2,100))
print(calFEdgeness(img3,100))
print('-------------------------------------- end edge')
print(calTextureHis(img1))
print(calTextureHis(img2))
print(calTextureHis(img3))
print('-------------------------------------- end texture')
print(calL1Dist(calTextureHis(img1),calTextureHis(img2)))
print(calL1Dist(calTextureHis(img1),calTextureHis(img3)))
print(calL1Dist(calTextureHis(img2),calTextureHis(img3)))
print("----------------------------------------- end his")

showimg1=cv2.imread('img1.jpg', cv2.IMREAD_GRAYSCALE)
showimg2=cv2.imread('img2.jpg', cv2.IMREAD_GRAYSCALE)
showimg3=cv2.imread('img3.jpg', cv2.IMREAD_GRAYSCALE)

# result=textureOverlay(showimg1,img1,0.2)
# cv2.imshow('img1_0.2',result) 
# result=textureOverlay(showimg1,img1,0.3)
# cv2.imshow('img1_0.3',result) 
# result=textureOverlay(showimg1,img1,0.4)
# cv2.imshow('img1_0.4',result) 
# result=textureOverlay(showimg2,img2,0.2)
# cv2.imshow('img1_0.2',result) 
# result=textureOverlay(showimg2,img2,0.3)
# cv2.imshow('img1_0.3',result) 
# result=textureOverlay(showimg2,img2,0.4)
# cv2.imshow('img1_0.4',result) 

result=textureOverlay(showimg3,img3,0.2)
cv2.imshow('img1_0.2',result) 
result=textureOverlay(showimg3,img3,0.3)
cv2.imshow('img1_0.3',result) 
result=textureOverlay(showimg3,img3,0.4)
cv2.imshow('img1_0.4',result) 
cv2.waitKey(0)
