#  데이터

딥러닝에서 신경망을 학습하고 평가하는데 사용하는 data set의 종류



### train set

`학습에 사용하는 유일한 데이터 셋`

### test set

`학습과 검증이 완료된 모델의 성능을 평가하기 위한 데이터 셋`

`학습에 전혀 관여하지 않음 오로지 검증을 위해서만 사용 `

### validation set

`이미 학습이 완료된 모델을 검증하기 위한 데이터 셋`

```
train set으로 학습을 할때 너무 높은 epoch로 학습시키면 overfitting의 문제
validation set이 적절한 epoch를 찾아주고 그만큼만 train set으로 학습하게 해줌. 실제로는 모델자체에 학습시키는데 사용되는 데이터 셋은 아니지만 이런식으로 학습에 관여 한다.
```



# 음성 데이터 전처리 과정 살펴보기

`output = '/content/gdrive/MyDrive/file_example_WAV_2MG.wav'`

적당한 음성 파일을 다운받아 경로 설정해주기

```python
from scipy import signal
# 음성 파일 load
sig, sr = librosa.load(output, sr=22050)
```

음성파일 로드

### Waveform 과 mel-spectrogram

![image-20210907181901774](readme.assets\image-20210907181901774.png)



![image-20210907182400833](readme.assets\image-20210907182400833.png)

### **푸리에 변환**(**Fourier transform**, FT)

시간이나 공간에 대한 함수를 시간 또는 공간 주파수 성분으로 분해하는 변환

```python
# 단순 퓨리에 변환으로 스펙트럼 구하기
fft = np.fft.fft(sig)
```

X 축은 시간에서 주파수로 되고, Y축은 원래 Y축값에 대한 주파수상의 분포를 의미

### 단시간 푸리에 변환(**STFT**)

시간이 지남에 따라 신호의 국부 섹션에 대한 사인파 주파수 및 위상 내용을 결정하는 데 사용되는 푸리에 관련 변환. 노이즈 감소, 피치 감지, 피치 이동 등의 효과의 음성 응용 분야에서 많이 사용

### MFCC 

오디오 신호에서 추출할 수 있는 feature로, 소리의 고유한 특징을 나타내는 수치입니다. 주로 음성 인식, 화자 인식, 음성 합성, 음악 장르 분류 등 오디오 도메인의 문제를 해결하는 데 사용

![image-20210907182629155](readme.assets\image-20210907182629155.png)

