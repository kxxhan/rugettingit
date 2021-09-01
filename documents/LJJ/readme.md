# 01. Anaconda

Download :link: https://www.anaconda.com/products/individual

```bash
% conda-env list # 존재하는 가상환경리스트 조회
% conda list # 현재 환경에 설치된 패키지 확인
% conda create -n mini-ai python=3.7 # 새로운 가상환경 생성
% conda activate mini-ai # 생성한 가상환경 활성화
% conda install pytorch torchvision torchaudio cudatoolkit=10.1 -c pytorch 		 # cudatoolkit은 GPU가 없는 경우 설치X
						#전체한번에 설치가 안되는 경우 각각 설치 해보기
% pip3 install tensorflow # tensorflow 설치

```



# 1-1 Dataset 준비, 전처리

```python
# torch.utils.data.DataLoader class

DataLoader(dataset, batch_size=1, shuffle=False, sampler=None,
           batch_sampler=None, num_workers=0, collate_fn=None,
           pin_memory=False, drop_last=False, timeout=0,
           worker_init_fn=None, *, prefetch_factor=2,
           persistent_workers=False)
```

- `dataset` :  DataLoader클래스의 가장 중요한 파라미터는 dataset이다. 어디서 부터 데이터셋 객체를 가져오는지를 말한다. 두 종류의 데이터셋 타입이 있다.
  - [map-style datasets](https://pytorch.org/docs/stable/data.html#map-style-datasets),
  - [iterable-style datasets](https://pytorch.org/docs/stable/data.html#iterable-style-datasets).

```python
# torchvision으로 CIFAR10 testset load, trainset dataloader 정의
testset = torchvision.datasets.CIFAR10(root='./data', train=False, download=True, transform=transform)
testset.__getitem__(18)
# 생성된 데이터셋인 testset이 어떻게 생겼는지 확인해보기
```

<img src="readme.assets/image-20210901133850754.png" alt="image-20210901133850754" style="zoom:50%;" />



```python
# Req. 1-1	데이터셋 준비 및 전처리
# 이미지를 시각화하는 함수
def visualize(img):
    pass
    # [-1, -1] 범위로 normalize된 데이터를 [0,1] 범위로 unnormalize
    img = img / 2 + 0.5 # [-0.5, 0.5] 인것에 0.5 를 더해 [0, 1]로 normalize
    
    # img를 numpy값으로 변환
    img = img.numpy()

    # plt.imshow함수로 시각화
    plt.imshow(np.transpose(img))

# 트레이닝 데이터를 랜덤 샘플
dataiter = iter(trainloader)
images, labels = dataiter.next() ## image

# show images
visualize(torchvision.utils.make_grid(images))

# print labels
print(' '.join('%5s' % classes[labels[j]] for j in range(4)))
```

![image-20210831213812660](readme.assets/image-20210831213812660.png)



# 1-2 분류기 모델 설계

- CNN (Convolutional Neural Network)를 사용하여 모델 설계합니다.

- 이미지는 3-channel을 입력으로 받고, 최종적으로 10개의 class에 대한 확률을 구합니다.

  ```python
  # 앞서서 정의한 클래스 
  # CIFAR10의 10개의 class 정의
  classes = ('plane', 'car', 'bird', 'cat',
             'deer', 'dog', 'frog', 'horse', 'ship', 'truck')
  ```

- 아래의 모델은 예시이므로, layer를 직접 추가해보면서 실험하시길 바랍니다.

```
Conv2d(30, 16, kernel_size=(5, 5), stride=(1, 1))
MaxPool2d(kernel_size=2, stride=2, padding=0, dilation=1, ceil_mode=False)
Linear(in_features=3136, out_features=128, bias=True)
Linear(in_features=128, out_features=10, bias=True)
```

# 1-3 모델 학습

- Loss function, Optimizer정의

  ```python
  import torch.optim as optim
  
  # Req. 1-3	Loss function 및 optimizer정의
  
  # loss function
  criterion = nn.CrossEntropyLoss()
  
  # optimizer
  optimizer = optim.SGD(classifier.parameters(), lr=0.001, momentum=0.9)
  ```

  

```python
# Req. 1-4	모델 학습
epochs = 100  #define epochs

# 1) for문으로 epochs 만큼 반복
for epoch in range(epochs):  # epochs 횟수만큼 반복
    
    # loss값 누적 
    running_loss = 0.0
    
    # 2) for문으로 trainset이 저장되어 있는 trainloader에서 배치 사이즈 만큼씩 샘플링하여 data load
    for i, data in enumerate(trainloader, 0):
        
        # 3) load한 data에서 input 값과 label로 분리하여 저장
        inputs, labels = data
       
        # 4) 각각의 값을 device에 올린다 (GPU or CPU)
        # inputs, labels = data[0].to(device), data[1].to(device)

        # 5) optimizer에서 gradient 값 0으로 초기화
        optimizer.zero_grad()

        # 6) model에 input값을 입력하여 forward propagation
  
        outputs = classifier(inputs)
        # 7)  loss function으로 예측값과 label 비교
        loss = criterion(outputs, labels)
        
        # 8) loss 값 backpropagation 하여 gradient 계산
        loss.backward()

        # 9) 계산된 gradient를 모두 parameter에 적용
        optimizer.step()


        # 10) loss 값을 합하여 일정 주기(ex.2000 batch) 마다 평균 loss 값 출력 후 초기화
        running_loss += loss.item()
        if i % 2000 == 1999:    # print every 2000 mini-batches
            print('[%d, %5d] loss: %.3f' %
                  (epoch + 1, i + 1, running_loss / 2000))
            running_loss = 0.0

# 12) torch.save로 학습이 마친 이후 모델 저장        
PATH = './cifar_net.pth'
torch.save(classifier.state_dict(), PATH)

print('Finished Training')
```

