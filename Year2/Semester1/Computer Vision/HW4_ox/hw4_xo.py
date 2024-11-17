import cv2
import numpy as np

def detect_grid(image):
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    edges = cv2.Canny(gray, 50, 150, apertureSize=3)
    lines = cv2.HoughLines(edges, 1, np.pi/180, 150)
    
    if lines is not None:
        for line in lines:
            rho, theta = line[0]
            a = np.cos(theta)
            b = np.sin(theta)
            x0 = a*rho
            y0 = b*rho
            x1 = int(x0 + 1000*(-b))
            y1 = int(y0 + 1000*(a))
            x2 = int(x0 - 1000*(-b))
            y2 = int(y0 - 1000*(a))
            cv2.line(image, (x1, y1), (x2, y2), (0, 0, 255), 2)
    else:
        print("x")
    
    return image

def classify_symbol(cell):
    gray = cv2.cvtColor(cell, cv2.COLOR_BGR2GRAY)
    _, thresh = cv2.threshold(gray, 128, 255, cv2.THRESH_BINARY_INV)
    
    # ตรวจจับวงกลม
    circles = cv2.HoughCircles(gray, cv2.HOUGH_GRADIENT, 1, 20,param1=50, param2=15, minRadius=10, maxRadius=40)
    
    # ตรวจจับเส้นตรง
    edges = cv2.Canny(thresh, 50, 150, apertureSize=3)
    lines = cv2.HoughLinesP(edges, 1, np.pi/180, 60, minLineLength=5, maxLineGap=10)
    
    if circles is not None and lines is not None:
        # ถ้าตรวจพบทั้งวงกลมและเส้นตรง ให้ตัดสินใจจากจำนวนเส้น
        if len(lines) >= 2:
            return "X"
        else:
            return "O"
    elif circles is not None:
        return "O"
    elif lines is not None and len(lines) >= 2:
        return "X"

    return "?"

def analyze_tictactoe(imagepath):
    image = cv2.imread(imagepath)
    
    image = detect_grid(image)
    height, width, _ = image.shape
    cell_height = height // 3
    cell_width = width // 3
    
    for i in range(3):
        for j in range(3):
            y_start = i * cell_height
            y_end = (i + 1) * cell_height
            x_start = j * cell_width
            x_end = (j + 1) * cell_width
            
            cell = image[y_start:y_end, x_start:x_end]
            symbol = classify_symbol(cell)
            print(f"ช่อง ({i},{j}): {symbol}")
            cv2.putText(image, symbol, (x_start + 10, y_start + 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2)
    
    cv2.imshow("Analyzed Tic-Tac-Toe", image)
    cv2.waitKey(0)
    cv2.destroyAllWindows()

# เรียกใช้ฟังก์ชัน
analyze_tictactoe(r"D:\KU KPS\Comvision\HW4_ox\ox\ox1.jpg")