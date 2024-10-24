import cv2
import matplotlib.pyplot as plt
import numpy as np
img_bgr= cv2.imread("photo.jpg")
img_rgb= cv2.cvtColor(img_bgr,cv2.COLOR_BGR2RGB)
plt.figure("RGB")
plt.imshow(img_rgb) 