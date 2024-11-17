import cv2
import matplotlib.pyplot as plt
import numpy as np

img_bgr= cv2.imread("minion.jpg")
img_rgb= cv2.cvtColor(img_bgr,cv2.COLOR_BGR2RGB)
plt.figure("RGB")
plt.imshow(img_rgb)
plt.show()

def toGray(img):
    img_gray=cv2.cvtColor(img,cv2.COLOR_RGB2GRAY)
    return img_gray
img_gray = toGray(img_rgb)
plt.figure('Gray')
plt.imshow(img_gray, cmap='gray')
plt.show()

img_GrayF = np.full((img_gray.shape[0],img_gray.shape[1]),0,dtype=np.uint8) 
def treshold(img):
    for i in range(len(img)):
        for j in range(len(img [i])):
            if img [i][j]<50:
                img_GrayF[i][j]=0
            elif img [i][j]>200:
                img_GrayF[i][j]=255
            else:
                img_GrayF[i][j]=((img [i][j]-50)*1.7)
treshold(img_gray)
plt.figure('Gray Function')
plt.imshow(img_GrayF,cmap='gray')
plt.show()

plt.figure('Histogram')
def histoGram(img):
    high=img.shape[0]
    width = img.shape[1]
    his = np.full(256,0,dtype=int)
    for i in range(high):
        for j in range(width):
            his[img [i][j]]+=1
        return his
h=histoGram(img_GrayF)
x = range(256)
plt.bar(x,h)
plt.title('Histogram')
plt.show()