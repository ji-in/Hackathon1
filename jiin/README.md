# 프로젝트 : 포레스텔라 분류기 만들기

## '포레스텔라'란?
크로스오버 남성 4중창 보컬그룹이다. 조민규, 배두훈, 강형호, 고우림으로 구성된다.  
이 4명의 얼굴 사진을 분류하고자 한다.

## 1. 데이터셋 수집하기

[이미지 데이터를 크롤링하기 위해 사용한 코드](https://github.com/ji-in/Hackathon1/blob/main/jiin/crawling.py)

[크롤링하기 위해 사용한 코드 crawler.py 코드 설명](https://github.com/ji-in/Hackathon1/blob/main/jiin/crawler_review.md)

## 2. 데이터셋 전처리 하기

1. 눈으로 확인해서 원하는 이미지가 아닌 것은 제외시키기

2. 얼굴만 crop 하기

   [얼굴만 검출해서 crop하기 위해 사용한 코드](https://github.com/ji-in/Hackathon1/blob/main/jiin/crop.py)

   [크롭하기 위해 사용한 crop.py 코드 설명](https://github.com/ji-in/Hackathon1/blob/jiin/jiin/crop_review.md)

   - dlib 또는 opencv를 사용해서 crop 하기
   - Reference : [ref1](https://m.blog.naver.com/PostView.naver?blogId=dic1224&logNo=221073987368&proxyReferer=https:%2F%2Fwww.google.com%2F), [ref2](https://jngmk.netlify.app/dev/python/2020-03-19-face-recognition-with-openCV-and-dlib)

3. 마지막으로 눈으로 한번 더 이미지 거르기

4. Training dataset, validation dataset, test dataset 8:1:1로 나누기

## 3. 전이학습시 사용할 모델 선택하기

**사용할 모델 : resnet18**

**이유**

 	1. Pytorch에 resnet을 쉽게 사용할 수 있는 함수가 있다.
 	2. 마지막 ILSVRC 대회인 ILSVRC 2015에서 우승했다. 즉, 성능이 좋다.

## 4. 모델 만들기

**모델에 반드시 들어가야 할 것**

- [x] Class를 사용해서 코드 짜기
- [x] GPU 가속 버전으로 코드 짜기
- [x] Validation 추가하기
- [x] progress bar 추가하기
- [x] evaluation 추가하기

## 5. Training 하기

[Train 코드 설명](https://github.com/ji-in/Hackathon1/blob/main/jiin/code_review.md)

## 6. Test 하기

<img src=".\test.png" style="zoom:72%;" />

꽤 잘 맞추는 것을 볼 수 있다. (근데, 데이터를 잘못 수집했나... 2행 1열 이미지 배두훈같이 안생겼다...)

## 7. Reference

[파이토치 공식 홈페이지 코드를 참고했다](https://tutorials.pytorch.kr/beginner/transfer_learning_tutorial.html)

## 8. 프로젝트 하면서 추가적으로 알게된 것

[파이참 가상환경 만들기](https://bskyvision.com/946)

[이미지 여러개 로드하기 - 답변 #4](https://www.python2.net/questions-57830.htm)

[argparse 사용법](https://docs.python.org/ko/3.7/library/argparse.html)

[모델 저장하고 불러오기](https://tutorials.pytorch.kr/beginner/saving_loading_models.html)