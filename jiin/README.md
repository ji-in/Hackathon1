# 프로젝트 : 포레스텔라 분류기 만들기



## 1. 데이터셋 수집하기

[이미지 데이터를 크롤링하기 위해 사용한 코드](https://github.com/ji-in/Hackathon1/blob/main/jiin/crawling.py)

## 2. 데이터셋 전처리 하기

1. 눈으로 확인해서 원하는 이미지가 아닌 것은 제외시키기 -> 완료
2. 얼굴만 crop 하기 -> [얼굴만 검출해서 crop하기 위해 사용한 코드](https://github.com/ji-in/Hackathon1/blob/main/jiin/crop.py)
   - dlib 또는 opencv를 사용해서 crop 하기 -> 완료
   - Reference : [ref1](https://m.blog.naver.com/PostView.naver?blogId=dic1224&logNo=221073987368&proxyReferer=https:%2F%2Fwww.google.com%2F), [ref2](https://jngmk.netlify.app/dev/python/2020-03-19-face-recognition-with-openCV-and-dlib), [ref3](https://jjeamin.github.io/posts/FaceNet/)
3. 마지막으로 눈으로 한번 더 이미지 거르기
4. 이미지 크기 통일시키기
5. Training dataset, validation dataset, test dataset 60:20:20 으로 나누기

## 3. 전이학습시 사용할 모델 선택하기

**사용할 모델 : resnet18**

**이유**

 	1. Pytorch에 resnet을 쉽게 사용할 수 있는 함수가 있다.
 	2. 마지막 ILSVRC 대회인 ILSVRC 2015에서 우승했다. 즉, 성능이 좋다.

## 4. 모델 만들기

**모델에 반드시 들어가야 할 것**

*위에서 아래로 갈 수록 중요도가 떨어진다.*

- [ ] Custom dataset 클래스 만들기
- [ ] Class를 사용해서 코드 짜기
- [ ] Class를 사용해서 코드 짜기
- [x] GPU 가속 버전으로 코드 짜기
- [x] Validation 추가하기
- [ ] progress bar 추가하기 -> tqdm 사용하면 될 것 같다.
- [ ] 파라미터 초기화 추가하기 (ex. He 초기화)
- [x] evaluation 추가하기

## 5. Training 하기



## 6. Test 하기

<img src=".\test.png" style="zoom:72%;" />

꽤 잘 맞추는 것을 볼 수 있다.

## 7. Reference

[파이토치 공식 홈페이지 코드를 참고했다](https://tutorials.pytorch.kr/beginner/transfer_learning_tutorial.html)

## 8. 프로젝트 하면서 추가적으로 알게된 것

[파이참 가상환경 만들기](https://bskyvision.com/946)

[이미지 여러개 로드하기 - 답변 #4](https://www.python2.net/questions-57830.htm)