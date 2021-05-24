# 얼굴 이미지 데이터 얼굴만 crop하기

[참고한 블로그](https://m.blog.naver.com/PostView.naver?blogId=dic1224&logNo=221073987368&proxyReferer=https:%2F%2Fwww.google.com%2F)

### 사용한 라이브러리

```python
import dlib, cv2, glob
```

### dlib 라이브러리를 사용해 얼굴만 검출하기

```python
face_detector = dlib.get_frontal_face_detector()
```

### 크롭하기

이미지를 여러개 불러온다 -> 크롭한다 -> 크롭한 결과를 저장한다.

```python
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
```

