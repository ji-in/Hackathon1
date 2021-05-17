# 프로젝트 : 포레스텔라 분류기 만들기



## 1. 데이터셋 수집하기

수집중

## 2. 데이터셋 전처리 하기

1. Training dataset, validation dataset, test dataset 60:20:20 으로 나누기
2. 얼굴만 crop 하기

## 3. 전이학습시 사용할 모델 선택하기

**사용할 모델 : resnet18**

**이유**

 	1. Pytorch에 resnet을 쉽게 사용할 수 있는 함수가 있다.
 	2. 마지막 ILSVRC 대회인 ILSVRC 2015에서 우승했다. 즉, 성능이 좋다.

## 4. 모델 만들기

**모델에 반드시 들어가야 할 것**

*위에서 아래로 갈 수록 중요도가 떨어진다.*

- [ ] Custom dataset 클래스 만들기
- [ ] GPU 가속 버전으로 코드 짜기
- [ ] Validation 코드 추가하기
- [ ] progress bar 추가하기 -> tqdm 사용하면 될 것 같다.
- [ ] 파라미터 초기화 추가하기 (ex. He 초기화)
- [ ] evaluation 하는 부분 추가하기

## 5. Training 하기



## 6. Test 하기

