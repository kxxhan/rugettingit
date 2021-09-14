> K8s라고도 알려진 쿠버네티스는 컨테이너화된 애플리케이션을 자동으로 배포, 스케일링 및 관리해주는 오픈소스 시스템

https://kubernetes.io/ko/docs/concepts/overview/what-is-kubernetes/#여정-돌아보기

전통적 배포 → 가상머신(개별 OS)

### → 컨테이너(OS 공유)

VM과 유사하지만 격리 속성을 완화하여 애플리케이션 간에 운영체제(OS)를 공유/ 가볍다

자체 파일 시스템, CPU 점유율, 메모리, 프로세스 공간 등이 있다. 기본 인프라와의 종속성을 끊었기 때문에, 클라우드나 OS 배포본에 모두 이식할 수 있음

- 컨테이너 이미지 생성이 보다 쉽고 효율적
- 지속적인 개발, 통합 및 배포: 효율적으로 롤백
- 개발과 운영의 관심사 분리
  - 배포 시점이 아닌 빌드/릴리스 시점에 애플리케이션 컨테이너 이미지를 만들기 때문에, 애플리케이션이 인프라스트럭처에서 분리
- 개발, 테스팅 및 운영 환경에 걸친 일관성
- 클라우드 및 OS 배포판 간 이식성
- 애플리케이션 중심 관리
- 독립적인 단위로 쪼개져서 동적으로 배포되고 관리될 수 있다.
- 리소스 격리: 애플리케이션 성능을 예측할 수 있다.
- 자원 사용량: 리소스 사용량: 고효율 고집적.

### → 쿠버네티스

예를 들어 컨테이너가 다운되면 다른 컨테이너를 다시 시작해야 한다. 이 문제를 시스템에 의해 처리한다면 더 쉽지 않을까?

쿠버네티스는 분산 시스템을 탄력적으로 실행하기 위한 프레임 워크를 제공한다. 애플리케이션의 확장과 장애 조치를 처리하고, 배포 패턴 등을 제공

- Kubernetes can ...

  - **서비스 디스커버리와 로드 밸런싱**

    쿠버네티스는 DNS 이름을 사용하거나 자체 IP 주소를 사용하여 컨테이너를 노출할 수 있다.

    컨테이너에 대한 트래픽이 많으면, 쿠버네티스는 네트워크 트래픽을 로드밸런싱하고 배포

  - **스토리지 오케스트레이션**

    로컬 저장소, 공용 클라우드 공급자 등과 같이 원하는 저장소 시스템을 자동으로 탑재

  - **자동화된 롤아웃과 롤백**

    배포된 컨테이너의 원하는 상태를 서술할 수 있으며 현재 상태를 원하는 상태(desire state)로 설정한 속도에 따라 변경

    *예를 들어 쿠버네티스를 자동화해서 배포용 새 컨테이너를 만들고, 기존 컨테이너를 제거하고, 모든 리소스를 새 컨테이너에 적용할 수 있다.*

  - **자동화된 빈 패킹(bin packing)**

    컨테이너화된 작업을 실행하는데 사용할 수 있는 쿠버네티스 클러스터 노드를 제공

    각 컨테이너가 필요로 하는 CPU와 메모리(RAM)를 쿠버네티스에게 지시

    쿠버네티스는 컨테이너를 노드에 맞추어서 리소스를 가장 잘 사용할 수 있도록 해줌

  - **자동화된 복구(self-healing)**

    쿠버네티스는 실패한 컨테이너를 다시 시작하고, 컨테이너를 교체하며, '사용자 정의 상태 검사'에 응답하지 않는 컨테이너를 죽이고, 서비스 준비가 끝날 때까지 그러한 과정을 클라이언트에 보여주지 않는다.

  - **시크릿과 구성 관리**

    쿠버네티스를 사용하면 암호, OAuth 토큰 및 SSH 키와 같은 중요한 정보를 저장하고 관리 가능

    컨테이너 이미지를 재구성하지 않고 스택 구성에 시크릿을 노출하지 않고도 시크릿 및 애플리케이션 구성을 배포 및 업데이트 가능

쿠버네티스는 전통적인, 모든 것이 포함된 Platform as a Service(PaaS)가 아니다

> 컨테이너 수준에서 운영되기 때문에, PaaS가 일반적으로 제공하는 배포, 스케일링, 로드 밸런싱과 같은 기능을 제공하며, 사용자가 로깅, 모니터링 및 알림 솔루션을 통합할 수 있다. 하지만, 쿠버네티스는 모놀리식(monolithic)이 아니어서, 이런 기본 솔루션이 선택적이며 추가나 제거가 용이하다.

> 쿠버네티스는 독립적이고 조합 가능한 제어 프로세스들로 구성되어 있다. 이 프로세스는 지속적으로 현재 상태를 입력받은 의도한 상태로 나아가도록 한다

### 쿠버네티스 컴포넌트

쿠버네티스를 배포하면 **클러스터**를 얻는다.

쿠버네티스 클러스터는

컨테이너화된 애플리케이션을 실행하는 [노드](https://kubernetes.io/ko/docs/concepts/architecture/nodes/)라고 하는 워커 머신의 집합.

모든 클러스터는 최소 한 개의 워커 노드를 가진다.

**워커 노드**는 애플리케이션의 구성요소인 **파드**를 호스트한다. **컨트롤 플레인**은 워커 노드와 클러스터 내 파드를 **관리**한다.

https://kubernetes.io/ko/docs/concepts/overview/components/

## **컨트롤 플레인 컴포넌트**

컨트롤 플레인 컴포넌트는 클러스터에 관한 전반적인 결정(예를 들어, 스케줄링)을 수행하고 클러스터 이벤트(예를 들어, 디플로이먼트의 `replicas` 필드에 대한 요구 조건이 충족되지 않을 경우 새로운 [파드](https://kubernetes.io/ko/docs/concepts/workloads/pods/)를 구동시키는 것)를 감지하고 반응한다.

### **kube-apiserver**

API 서버는 쿠버네티스 API를 노출하는 쿠버네티스 [컨트롤 플레인](https://kubernetes.io/ko/docs/reference/glossary/?all=true#term-control-plane) 컴포넌트이다. API 서버는 쿠버네티스 컨트롤 플레인의 프론트 엔드이다.

클러스터의 다른 부분 그리고 외부 컴포넌트가 서로 통신할 수 있도록 HTTP API를 제공

쿠버네티스 API를 사용하면 쿠버네티스의 API 오브젝트(예: 파드(Pod), 네임스페이스(Namespace), 컨피그맵(ConfigMap) 그리고 이벤트(Event))를 질의(query)하고 조작할 수 있다.

대부분의 작업은 kubectl 커맨드 라인 인터페이스 또는 API를 사용하는 kubeadm과 같은 다른 커맨드 라인 도구를 통해 수행

[OpenAPI 명세](https://kubernetes.io/ko/docs/concepts/overview/kubernetes-api/#api-specification) 🔗

### **etcd**

모든 클러스터 데이터를 담는 쿠버네티스 뒷단의 저장소로 사용되는 일관성·고가용성 키-값 저장소.

### **kube-scheduler**

[노드](https://kubernetes.io/ko/docs/concepts/architecture/nodes/)가 배정되지 않은 새로 생성된 [파드](https://kubernetes.io/ko/docs/concepts/workloads/pods/) 를 감지하고, 실행할 노드를 선택하는 컨트롤 플레인 컴포넌트.

### **kube-controller-manager**

[컨트롤러](https://kubernetes.io/ko/docs/concepts/architecture/controller/) 프로세스를 실행하는 컨트롤 플레인 컴포넌트.

## **노드 컴포넌트**

노드 컴포넌트는 동작 중인 파드를 유지시키고 쿠버네티스 런타임 환경을 제공하며, 모든 노드 상에서 동작한다.

### **kubelet**

클러스터의 각 [노드](https://kubernetes.io/ko/docs/concepts/architecture/nodes/)에서 실행되는 에이전트. Kubelet은 [파드](https://kubernetes.io/ko/docs/concepts/workloads/pods/)에서 [컨테이너](https://kubernetes.io/ko/docs/concepts/containers/)가 확실하게 동작하도록 관리한다.

### **kube-proxy**

kube-proxy는 클러스터의 각 [노드](https://kubernetes.io/ko/docs/concepts/architecture/nodes/)에서 실행되는 네트워크 프록시로, 쿠버네티스의 [서비스](https://kubernetes.io/docs/concepts/services-networking/service/) 개념의 구현부이다.

### **컨테이너 런타임**

컨테이너 런타임은 컨테이너 실행을 담당하는 소프트웨어이다.

## **애드온**

애드온은 쿠버네티스 리소스([데몬셋](https://kubernetes.io/ko/docs/concepts/workloads/controllers/daemonset), [디플로이먼트](https://kubernetes.io/ko/docs/concepts/workloads/controllers/deployment/) 등)를 이용하여 클러스터 기능을 구현한다. 이들은 클러스터 단위의 기능을 제공하기 때문에 애드온에 대한 네임스페이스 리소스는 `kube-system` 네임스페이스에 속한다.

### **DNS**

여타 애드온들이 절대적으로 요구되지 않지만, 많은 예시에서 필요로 하기 때문에 모든 쿠버네티스 클러스터는 [클러스터 DNS](https://kubernetes.io/ko/docs/concepts/services-networking/dns-pod-service/)를 갖추어야만 한다.

클러스터 DNS는 구성환경 내 다른 DNS 서버와 더불어, 쿠버네티스 서비스를 위해 DNS 레코드를 제공해주는 DNS 서버다.

쿠버네티스에 의해 구동되는 컨테이너는 DNS 검색에서 이 DNS 서버를 자동으로 포함한다.

### **웹 UI (대시보드)**

[대시보드](https://kubernetes.io/ko/docs/tasks/access-application-cluster/web-ui-dashboard/)는 쿠버네티스 클러스터를 위한 범용의 웹 기반 UI다. 사용자가 클러스터 자체뿐만 아니라, 클러스터에서 동작하는 애플리케이션에 대한 관리와 문제 해결을 할 수 있도록 해준다.

### **컨테이너 리소스 모니터링**

[컨테이너 리소스 모니터링](https://kubernetes.io/ko/docs/tasks/debug-application-cluster/resource-usage-monitoring/)은 중앙 데이터베이스 내의 컨테이너들에 대한 포괄적인 시계열 매트릭스를 기록하고 그 데이터를 열람하기 위한 UI를 제공해 준다.

### **클러스터-레벨 로깅**

[클러스터-레벨 로깅](https://kubernetes.io/ko/docs/concepts/cluster-administration/logging/) 메커니즘은 검색/열람 인터페이스와 함께 중앙 로그 저장소에 컨테이너 로그를 저장하는 책임을 진다.