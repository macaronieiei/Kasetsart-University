import cv2
import numpy as np

texture1 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\texture1.png")
texture1_gray = cv2.cvtColor(texture1, cv2.COLOR_BGR2GRAY)
texture2 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\texture2.png")
texture2_gray = cv2.cvtColor(texture2, cv2.COLOR_BGR2GRAY)
texture3 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\texture3.png")
texture3_gray = cv2.cvtColor(texture3, cv2.COLOR_BGR2GRAY)

def calMeanGray(img):
    mean = np.mean(img)
    normalize = mean / 255
    return (float)(normalize)

meanTexture1 = calMeanGray(texture1_gray)
meanTexture2 = calMeanGray(texture2_gray)
meanTexture3 = calMeanGray(texture3_gray)
print("MeanTexture1 : ", meanTexture1)
print("MeanTexture2 : ", meanTexture1)
print("MeanTexture3 : ", meanTexture1)

def calFEdgeness(img, threshold) :
    sobel = cv2.Sobel(img, cv2.CV_64F, 1, 1, ksize=5)
    sobelABS = np.abs(sobel)
    totalPixels = sobel.size
    totalEdgePixels = np.sum(sobelABS > threshold)
    fedgeness =  (float) (totalEdgePixels / totalPixels)
    return fedgeness

print("Fedgeness1 : ", calFEdgeness(texture1_gray, 100))
print("Fedgeness2 : ", calFEdgeness(texture2_gray, 100))
print("Fedgeness3 : ", calFEdgeness(texture3_gray, 100))

def calTextureHis(img):
    texture_histogram = np.array([])
    texture_histogram = np.append(texture_histogram, calMeanGray(img))
    texture_histogram = np.append(texture_histogram, calFEdgeness(img, 100))
    return texture_histogram

print("TextureHis1 : ", calTextureHis(texture1_gray))
print("TextureHis2 : ", calTextureHis(texture2_gray))
print("TextureHis3 : ", calTextureHis(texture3_gray))

def calL1Dist(his1,his2):
    L1Distnp = np.abs(his1[0]-his2[0])+np.abs(his1[1]-his2[1])
    return L1Distnp

print("L1 texture 1 vs texture2 : ",calL1Dist(calTextureHis(texture1_gray),calTextureHis(texture2_gray)))
print("L1 texture 1 vs texture3 : ", calL1Dist(calTextureHis(texture1_gray),calTextureHis(texture3_gray)))
print("L1 texture 2 vs texture3 : ",calL1Dist(calTextureHis(texture2_gray),calTextureHis(texture3_gray)))

def textureOvelay(img, texture, threshold) :
    imgOut = np.copy(img)
    hisTexture = calTextureHis(texture)
    windowSize = 21
    r, c = img.shape
    for i in range(r-windowSize+1):
        for j in range(c-windowSize+1):
            subImg = img[i:i+windowSize, j:j+windowSize]
            hisSubImg = calTextureHis(subImg)
            if calL1Dist(hisTexture, hisSubImg) < threshold:
                imgOut[i+windowSize//2][j+windowSize//2] = 255
    return imgOut

test1 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\img1.jpg" , cv2.IMREAD_GRAYSCALE)
test2 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\img2.jpg" , cv2.IMREAD_GRAYSCALE)
test3 = cv2.imread(r"D:\KU KPS\Comvision\imgTuexture\imgTexture\img3.jpg" , cv2.IMREAD_GRAYSCALE)

#result=textureOvelay(test1,texture1_gray,0.2)
#cv2.imshow('texture1_0.2',result) 
#result=textureOvelay(test1,texture1_gray,0.3)
#cv2.imshow('texture1_0.3',result) 
#result=textureOvelay(test1,texture1_gray,0.4)
#cv2.imshow('texture1_0.4',result) 
#cv2.waitKey(0)

#result=textureOvelay(test2,texture2_gray,0.2)
#cv2.imshow('texture1_0.2',result) 
#result=textureOvelay(test2,texture2_gray,0.3)
#cv2.imshow('texture1_0.3',result) 
#result=textureOvelay(test2,texture2_gray,0.4)
#cv2.imshow('texture1_0.4',result) 
#cv2.waitKey(0)
