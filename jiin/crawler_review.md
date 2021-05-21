# 이미지 데이터 크롤링하기

[참고한 유튜브](https://youtu.be/1b7pXC1-IbE)

이 코드는 Selenium을 사용한다. `pip install selenium`으로 셀레니움을 다운받을 수 있다.

셀레니움으로 활용할 웹브라우저는 chrome을 선택했다. 현재 가지고 있는 chrome의 버전에 맞는 chromedriver를 다운받고, 다운받은 chromedriver.exe 파일을 메인코드를 작성할 파이썬 파일과 같은 경로에 놓는다.

코드에서 나오는 생전 처음보는 것(나는 한번도 웹을 배운적이 없다)은 f12를 눌렀을 때 나오는 '개발자 보기'를 사용하면 된다. '개발자 보기' 제일 왼쪽 위에 있는 버튼(inspector라고 하던가)을 사용하면 더 편하게 시각적으로 웹 요소들을 확인할 수 있다.

### 사용한 라이브러리

```python
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import urllib.request
```

### 검색하기

이 코드를 실행시키면, 자동으로 크롬이 켜지면서 지정한 웹페이지(나는 구글에서 오른쪽 상단의 `이미지`버튼을 클릭하면 나오는 웹페이지로 설정했다)로 들어가서 자동으로 지정된 검색어를 입력하여 검색을 시작한다.

```python
driver = webdriver.Chrome()
driver.get("https://www.google.co.kr/imghp?hl=ko&tab=wi&authuser=0&ogbl")
elem = driver.find_element_by_name("q")
elem.send_keys("배두훈")
elem.send_keys(Keys.RETURN)
```

### 스크롤 내리기

구글 이미지에서 이미지를 검색하면 최대 50개의 이미지밖에 다운을 못받는다. 그래서 스크롤을 내렸을 때 웹 페이지의 길이를 계산한 후, 그 길이까지 계속 이미지를 다운받는다. 스크롤을 내리는 도중 `결과 더보기` 버튼이 나오면, 그 버튼을 클릭하도록 했다.

```python
SCROLL_PAUSE_TIME = 1
# Get scroll height
last_height = driver.execute_script("return document.body.scrollHeight")
while True:
    # Scroll down to bottom
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    # Wait to load page
    time.sleep(SCROLL_PAUSE_TIME)
    # Calculate new scroll height and compare with last scroll height
    new_height = driver.execute_script("return document.body.scrollHeight")
    if new_height == last_height:
        try:
            driver.find_element_by_css_selector(".mye4qd").click()
        except:
            break
    last_height = new_height
```

### 이미지 저장하기

이미지 검색이 끝났으면, 이제 저장하면 된다. 원하는 폴더에 차근차근 저장하자.

```python
images = driver.find_elements_by_css_selector(".rg_i.Q4LuWd")
count = 1
for image in images:
    try:
        image.click()
        time.sleep(3)
        imgUrl = driver.find_element_by_css_selector(".n3VNCb").get_attribute("src")
        urllib.request.urlretrieve(imgUrl, "D:\\포레스텔라\\배두훈\\배두훈" + str(count) + ".jpg")
        count = count + 1
    except:
        pass

driver.close()
```

