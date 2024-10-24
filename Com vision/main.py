import os
import csv
from PIL import Image
import numpy as np

def process_image(image_path):
    try:
        with Image.open(image_path) as img:
            img_array = np.array(img)
            area = np.count_nonzero(img_array)
            y, x = np.nonzero(img_array)
            x_centroid = np.mean(x) if len(x) > 0 else 0
            y_centroid = np.mean(y) if len(y) > 0 else 0
            return area, x_centroid, y_centroid
    except Exception as e:
        print(f"Error processing {image_path}: {str(e)}")
        return None

def main():
    base_dir = "mnistTrain"
    output_file = "6621600330.csv"
    subdirs = [d for d in os.listdir(base_dir) if os.path.isdir(os.path.join(base_dir, d))]
    subdirs.sort(key=lambda x: int(x.split('_')[0]))
    with open(output_file, 'w', newline='') as csvfile:
        csvwriter = csv.writer(csvfile)
        csvwriter.writerow(['subfolder', 'filename', 'area', 'xCentroid', 'yCentroid'])
        for subdir in subdirs:
            subdir_path = os.path.join(base_dir, subdir)
            for filename in os.listdir(subdir_path):
                if filename.lower().endswith('.bmp'):
                    file_path = os.path.join(subdir_path, filename)
                    result = process_image(file_path)
                    if result:
                        area, x_centroid, y_centroid = result
                        csvwriter.writerow([subdir, filename, area, x_centroid, y_centroid])
    print(f"Saved to {output_file}")

if __name__ == "__main__":
    main()