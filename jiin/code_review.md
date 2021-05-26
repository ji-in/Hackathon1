# 모델 코드 분석

## train_model 분석

```python
def train_model(model, criterion, optimizer, scheduler, num_epochs=25):
    since = time.time()

    best_model_wts = copy.deepcopy(model.state_dict())
    best_acc = 0.0

    for epoch in range(num_epochs):
        print('Epoch {}/{}'.format(epoch, num_epochs - 1))
        print('-' * 10)

        # 각 에폭(epoch)은 학습 단계와 검증 단계를 갖습니다.
        for phase in ['train', 'valid']:
            if phase == 'train':
                model.train()  # 모델을 학습 모드로 설정
            else:
                model.eval()   # 모델을 평가 모드로 설정

            running_loss = 0.0
            running_corrects = 0

            # 데이터를 반복
            for inputs, labels in dataloaders[phase]:
                inputs = inputs.to(device)
                labels = labels.to(device)

                # 매개변수 경사도를 0으로 설정
                optimizer.zero_grad()

                # 순전파
                # 학습 시에만 연산 기록을 추적
                with torch.set_grad_enabled(phase == 'train'):
                    outputs = model(inputs)
                    _, preds = torch.max(outputs, 1)
                    loss = criterion(outputs, labels)

                    # 학습 단계인 경우 역전파 + 최적화
                    if phase == 'train':
                        loss.backward()
                        optimizer.step()

                # 통계
                running_loss += loss.item() * inputs.size(0)
                running_corrects += torch.sum(preds == labels.data)
            if phase == 'train':
                scheduler.step()

            epoch_loss = running_loss / dataset_sizes[phase]
            epoch_acc = running_corrects.double() / dataset_sizes[phase]

            print('{} Loss: {:.4f} Acc: {:.4f}'.format(
                phase, epoch_loss, epoch_acc))

            # 모델을 깊은 복사(deep copy)함
            if phase == 'valid' and epoch_acc > best_acc:
                best_acc = epoch_acc
                best_model_wts = copy.deepcopy(model.state_dict())

        print()

    time_elapsed = time.time() - since
    print('Training complete in {:.0f}m {:.0f}s'.format(
        time_elapsed // 60, time_elapsed % 60))
    print('Best val Acc: {:4f}'.format(best_acc))

    # 가장 나은 모델 가중치를 불러옴
    model.load_state_dict(best_model_wts)
    return model
```

------



```python
since = time.time() # 1

best_model_wts = copy.deepcopy(self.model.state_dict()) # 2
best_acc = 0.0 # 3
```

1. 현재 시간을 계산한다. -> 총 training 시간을 계산하기 위함이다.
2. best_model_wts 에 현재 모델의 상태(파라미터 정보)를 복사한다.
3. 정확도가 가장 클 때 정확도를 저장하기 위한 변수이다.

```python
# 각 에폭(epoch)은 학습 단계와 검증 단계를 갖습니다.
for phase in ['train', 'valid']:
    if phase == 'train':
        model.train()  # 모델을 학습 모드로 설정
    else:
        model.eval()   # 모델을 평가 모드로 설정

    running_loss = 0.0
    running_corrects = 0
```

각 epoch마다 학습과 검증을 한다.

`train`단계이면 모델을 train 모드로 설정하고, `valid`단계이면 모델을 eval 모드로 설정한다.

`eval()` 함수는 evaluation 과정에서 사용하지 않아야 하는 layer들을 알아서 off 시키도록 하는 함수이다.

```python
# 데이터를 반복
for inputs, labels in dataloaders[phase]:
    inputs = inputs.to(device)
    labels = labels.to(device)

    # 매개변수 경사도를 0으로 설정
    optimizer.zero_grad()

    # 순전파
    # 학습 시에만 연산 기록을 추적
    with torch.set_grad_enabled(phase == 'train'):
        outputs = model(inputs)
        _, preds = torch.max(outputs, 1)
        loss = criterion(outputs, labels)

        # 학습 단계인 경우 역전파 + 최적화
        if phase == 'train':
            loss.backward()
            optimizer.step()

    # 통계
    running_loss += loss.item() * inputs.size(0)
    running_corrects += torch.sum(preds == labels.data)
```

`optimizer.zero_grad()`

파이토치에서는 gradients값들을 추후에 backward를 해줄 때 계속 더해주기 때문에, backpropagation을 하기 전에 항상 gradients를 0으로 zero로 만들어주고 시작을 해야한다. 

Iteration이 한번 끝나면 gradients를 항상 zero로 만들어 주어야 한다. 만약 gradients를 zero로 초기화하지 않으면 gradients가 의도한 방향과 다른 방향을 가르켜 학습이 원하는 방향으로 이루어지지 않는다.

------

`torch.set_grad_enabled(mode)`

mode에 따라 gradient 계산을 키고 끈다. `train`일 때는 키고, `valid`일 때는 끈다.

------

`torch.max(outputs, 1)`

`torch.max` 함수는 주어진 텐서 배열의 최대 값이 들어있는 index를 리턴하는 함수이다.

[torch.max](https://pytorch.org/docs/stable/generated/torch.max.html)

```python
if phase == 'train':
    scheduler.step()

epoch_loss = running_loss / dataset_sizes[phase]
epoch_acc = running_corrects.double() / dataset_sizes[phase]

print('{} Loss: {:.4f} Acc: {:.4f}'.format(
    phase, epoch_loss, epoch_acc))

# 모델을 깊은 복사(deep copy)함
if phase == 'valid' and epoch_acc > best_acc:
    best_acc = epoch_acc
    best_model_wts = copy.deepcopy(model.state_dict())
```

`valid`일 때, epoch 마다 계산한 정확도가 best_acc 보다 크면 best_acc를 갱신한다.

```python
time_elapsed = time.time() - since
    print('Training complete in {:.0f}m {:.0f}s'.format(
        time_elapsed // 60, time_elapsed % 60))
    print('Best val Acc: {:4f}'.format(best_acc))
```

`현재 시간 - 시작시간` 으로 training을 얼마나 했는지 계산한다.

```python
# 가장 나은 모델 가중치를 불러옴
model.load_state_dict(best_model_wts)
return model
```

`best_model_wts`에 가장 나은 모델의 파라미터들이 들어있다. 그것을 불러오고 모델을 반환한다.