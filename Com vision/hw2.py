import cv2
import matplotlib.pyplot as plt
import numpy as np

img_bgr= cv2.imread("minion.jpg")
img_rgb= cv2.cvtColor(img_bgr,cv2.COLOR_BGR2RGB)
plt.figure("RGB")
plt.imshow(img_rgb)
plt.show()

def toGray(img): 
    img=cv2.cvtColor(img,cv2.COLOR_RGB2GRAY)
    return img
img_gray = toGray(img_rgb)
plt.figure('Gray Test')
plt.imshow(img_gray,cmap='gray')
plt.show()

def toBlackWh(img): 
    for i in range(len(img)):
        for j in range(len(img [i])):
            if img [i][j] <190:
                img [i][j]=0
            else:
                img [i][j]=255
    return img
img_BW = toBlackWh(img_gray)
plt.figure('B&W')
plt.imshow(img_BW,cmap='gray')
plt.show()

area=0
for i in range(len(img_BW)):
    for j in range(len(img_BW[i])):
        if img_BW[i][j]==255:
            area+=1
print('Area : ',area)

cenX=0
cenY=0
for i in range(len(img_BW)):
    for j in range(len(img_BW[i])):
        if img_BW[i][j]==255:
            cenX+=j
            cenY+=i
print('Centroid : x=',cenX/area,' , y=',cenY/area)

horz =np.full((img_BW.shape[0],img_BW.shape[1]),0,dtype=np.uint8)
for i in range(len(img_BW)):
    objSpace=0
    for j in range(len(img_BW[i])):
        if img_BW[i][j]==255:
            objSpace+=1
            for j in range(objSpace-1):
                horz[i][j]=255
plt.figure('Horizon Projection')
plt.imshow(horz,cmap='gray')
plt.show()

valt =np.full((img_BW.shape[0],img_BW.shape[1]),0,dtype=np.uint8)
for j in range(len(img_BW[0])):
    objSpace=0
    for i in range(len(img_BW)):
        if img_BW[i][j]==255:
            objSpace+=1
            for i in range(objSpace-1): 
                valt[i][j]=255
plt.figure('Vertical Projection')
plt.imshow(valt,cmap='gray')
plt.show()

img_Redline =np.full((img_BW.shape[0],img_BW.shape[1],3),0,dtype=np.uint8)
for i in range(1,len(img_BW)-1):
    for j in range(1,len(img_BW[i])-1):
        if img_BW[i][j]==255:
            if img_BW[i][j-1]==0 or img_BW[i][j+1]==0 or img_BW[i-1][j]==0 or img_BW[i+1][j]==0:
                img_Redline[i][j][0]=255
                img_Redline[i][j][1]=0
                img_Redline[i][j][2]=0
plt.figure('Red Line')
plt.imshow(img_Redline)
plt.show()