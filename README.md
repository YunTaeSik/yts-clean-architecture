## Clean Architecture
  
 <div>
  <img src="https://user-images.githubusercontent.com/23161645/73226529-c3e90080-41b3-11ea-9f21-992c4c60335f.png" width = 250>
  <img src="http://miro.medium.com/max/1258/1*a-AUcEVdyRJhIepo9JyJBw.png" hspace=8 width = 250>
 </div>
     
     
 **Presentation Layer**  
   
 Presentation 레이어는 의존성이 높은 UI 레벨의 레이어입니다.  
현재 저는 MVVM패턴을 사용하고있습니다.  
 - [base (각종 Base 클래스)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/presentation/base)
 - [bidingAdapter (Android BindingAdapter)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/presentation/bindingAdapter) 
 - [di (Dagger2.0)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/presentation/di)
 - [ui (View, ViewModel)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/presentation/i)
   
   
 **Domain Layer**  
   
  Domain Layer는 순수한 Java나 Kotlin 모듈입니다. 그 이유는 도메인 레이어에서 일어나는 이유는 실제로 사용자가 하는 일련의 행동들을 적용하는 것인데 이 역시 안드로이드에 의존할 필요가 없기 때문입니다. 
저는 Entity, Response , Repository, UseCase를 담았습니다.
  - [entity (순수한 Entity값)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/domain/entity)
  - [response (API Response 객체)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/domain/response)
  - [repository (API Interface)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/domain/repository)
   - [usecase (UseCase Presentation과 연결부분)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/domain/usecase)
   
   
 **Data Layer**  
   
Data Layer 에서는 Domain Layer를 알고 있으므로  Domain Layer에 정의된 Repository를 실제로 구현을 하는 것입니다.   
여기에서는 Data Source에의 의존성이 생기므로 안드로이드 의존성이 생길 수 있습니다.
  - [repository (Domain repository를 실제로 구현하는부분)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/data/repository)
  - [source (Retrofit2.0 Service 구현)](https://github.com/YunTaeSik/STUnitasTest/tree/master/app/src/main/java/com/example/stunitastest/data/source/remote)

  
