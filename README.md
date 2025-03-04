# Film axtarışı və məlumatlandırma sistemi (With OMDB API)

**Film axtarışı və məlumatlandırma sistemi**, film axtarışı üçün hazırlanmış **Spring Boot** əsaslı bir backend sistemidir. Bu sistem istifadəçilərə **Filmləri xarici API vasitəsi ilə axtarmaq**, **Məlumatları uyğun fieldlərlə DB də saxlamaq** və **İstənilən filmə şərh yazmaq** imkanları yaradır.

## 📌 Xüsusiyyətlər
✅ **Filmləri əlavə etmə və silmə**
✅ **Filmləri API vasitəsilə əldə etmə**
✅ **İstəyə bağlı filmləri çeşidləmə, axtarma**
✅ **Məlumat bazası ilə əlaqə (MySQL dəstəyi)**
✅ **Spring Boot RESTful API ilə JSON formatında işləmə**

---

## 🚀 Quraşdırma və İstifadə
### 1️⃣ Layihəni Klonlayın
```sh
git clone https://github.com/istifadəçiadı  /budget-manager.git
cd budget-manager
```

### 2️⃣ Backend-i İşə Salın
Backend-in işləməsi üçün **Gradle və JDK 17+** lazımdır. Backend-i işə salmaq üçün:
```sh
cd backend
gradle bootRun
```
Bu əmrdən sonra **API (http://localhost:8080/com.aladdin/)** ünvanında işləyəcək.

---

## 🔗 API Əməliyyatları
Backend API aşağıdakı endpointləri təmin edir:

Ilkin olaraq qeyd etmək istəyirəm ki Büdcə yaratmadan xərc və gəlir yarada bilməzsiniz.
Əvvəlcə büdcə yaradılmalı və onun İD si ilə gəlir və xərc yaradılmalıdır.

### 📂 İstifadəçi Endpoints (`com.aladdin/movies_site/user`)
| Endpoint                              | Metod  | Açıqlama |
|---------------------------------------|--------|----------|
| `/`                                   | POST   | Yeni istifadəçi əlavə edir |
| `/{id}`                               | GET    | İstifadəçini gətirir |
| `/{userId}/movie/{movieId}`           | Post   | istifadəçiyə film əlavə etmə |
| `/userSMovie/{{id}}`                  | GET    | İstifadəçinin filmlərini gətirir |
| `/users`                              | GET    | Bütün istifadəçiləri gətirir |
| `/usersWithMovies`                    | GET    | İstifadəçilərin filmlərini gətirir |
| `/userId/{{id}}`                      | PUT    | İstifadəçiləni yeniləyir |
| `/{id}`                               | DELETE | İstifadəçini silir |
| `/id/{{id}}/{{moviesID}}/movieId`     | DELETE | İstifadəçinin filmlərini silir |
| `all`                                 | DELETE | Bütün istifadəçiləri silir |


### 📂 Film Endpoints (`com.aladdin/movies_site/movie`)
| Endpoint                  | Metod  | Açıqlama |
|---------------------------|--------|----------|
| `/income/new`             | POST   | Yeni gəlir əlavə edir |
| `/income/get/{id}`        | GET    | Müəyyən gəliri gətirir |
| `/income/allIncome`       | GET    | Bütün gəlirləri gətirir |
| `/income/update/{id}`     | PUT    | Gəliri yeniləyir |
| `/income/filterIncome`    | GET    | Tarixə görə gəlir axtarır |
| `/income/source/{source}` | GET    | Gəlir mənbəyinə görə axtarır |
| `/income/delete/{id}`     | DELETE | Müəyyən gəliri silir |
| `/income/deleteAll`       | DELETE | Bütün gəlirləri silir |

### 📂 Büdcə Endpoints (`aladdin.com/budget/`)
| Endpoint                  | Metod  | Açıqlama |
|---------------------------|--------|----------|
| `/budget/new`             | POST   | Yeni büdcə əlavə edir |
| `/budget/getAll`          | GET    | Bütün büdcələri gətirir |
| `/budget/incomeGroup`     | GET    | Gəlir qruplarını qaytarır |
| `/budget/expenseGroup`    | GET    | Xərc qruplarını qaytarır |
| `/budget/findById/{id}`   | GET    | Müəyyən büdcəni gətirir |
| `/budget/delete/All`      | DELETE | Bütün büdcələri silir |

Bütün API sorğuları **JSON formatında** olmalıdır və aşağıdakı nümunəyə uyğun gəlməlidir:
```json
{
  "description": "Maaş",
  "amount": 5000
}
```
Qeyd! 
Bəzi sorğular məsələn `/expense/filterDate` @RequestParam şəklində yazılır


## 🗄 Məlumat Bazası
Layihə **H2 (daxili verilənlər bazası)** və **MySQL** ilə işləyə bilər. 
Əgər **H2 istifadə edirsinizsə**, heç bir əlavə konfiqurasiya tələb olunmur. 
Əgər **MySQL istifadə edəcəksinizsə**, `application.properties` faylında **verilənlər bazası bağlantı parametrlərini** dəyişməlisiniz:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/budget_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

---

