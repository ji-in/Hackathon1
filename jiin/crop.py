import dlib, cv2, glob

face_detector = dlib.get_frontal_face_detector()

path = glob.glob("./포레스텔라/조민규/*.jpg")
cv_img = list()
count = 1
for img in path:
    
    image = cv2.imread(img)
    faces = face_detector(image)
    print("얼굴검출을 완료했습니다.")
    
    for f in faces:
        left = f.left()
        right = f.right()
        top = f.top()
        bottom = f.bottom()
        
        crop = image[top:bottom, left:right]
        try:
            cv2.imwrite("./포레스텔라/조민규_crop/" + str(count) + ".jpg", crop)
        except:
            pass
    print("Crop 이미지 저장을 완료했습니다.")
    
    count += 1