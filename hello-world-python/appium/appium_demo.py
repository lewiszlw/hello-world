# coding: utf-8

from appium import webdriver
import time

desired_caps={}
desired_caps['platformName']='Android'
desired_caps['version']='8.0.0'
desired_caps['deviceName']='26d855ff' # adb devices查看
# desired_caps['app'] = PATH('D:\\qq.apk')#被测试的App在电脑上的位置
desired_caps['appPackage'] = 'com.dianping.v1'
desired_caps['appActivity'] = 'com.dianping.main.guide.SplashScreenActivity'
driver=webdriver.Remote('http://127.0.0.1:4723/wd/hub',desired_caps)
print("============test==========")
time.sleep(10)
login_result = input("请先登录，是否已完成登录: [y/n]")
el1 = driver.find_element_by_xpath('//android.widget.ImageButton[@content-desc="霸王餐NEW"]')
el1.click()
print("进入霸王餐页面")
time.sleep(10)
el2 = driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.view.View[2]/android.view.View[2]')
el2.click()
print("进入0元秒杀页面")
time.sleep(8)
# driver.back()
# 获取所有商品
eles = []
for i in range(2, 4):
    try:
        element = driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View[{}]/android.view.View[2]/android.view.View[3]/android.view.View[2]'.format(i))
        eles.append(element)
    except Exception:
        print("exception i: {}".format(i))
print("eles size: {}".format(len(eles)))
for each in eles:
    each.click()
    time.sleep(1)