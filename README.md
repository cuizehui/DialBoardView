# DialBoardView
自定义拨号盘 

## Simple show
![simple pic](https://github.com/cuizehui/DialBoardView/blob/master/DialBoardView.png)

## view 功能

回调：

1. 获取输入框变化
2. 获取功能按键事件
3. 监听长按事件

```
public interface DialViewListener {

        /**
         * 输入内容变化
         */
        void inputChange();

        /**
         * 点击拨号按键
         */
        void onCall();

        /**
         * 点击设置按键
         */
        void onSetting();

        /**
         * 长按事件
         */
        void onLongEvent(String number);

    }
```


功能：

1. 获取输入框内容

```
mdialView.getNumber()
```

2. 清空输入框
      
```
    mdialView.cleanDialViewInput(); 
```

3. 设置输入框内容

```
mdialView.setDialViewInput("123456");
```

## 示例代码

```
 mdialView.setDialViewListener(new DialView.DialViewListener() {
            @Override
            public void inputChange() {
                Toast.makeText(MainActivity.this, mdialView.getNumber(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCall() {
                Toast.makeText(MainActivity.this, "onCall", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSetting() {
                Toast.makeText(MainActivity.this, "onSetting", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongEvent(String number) {
                Toast.makeText(MainActivity.this, "onLongEvent:" + number, Toast.LENGTH_SHORT).show();
                if (TextUtils.equals("1", number)) {
                    mdialView.setDialViewInput("123456");
                } else if (TextUtils.equals("2", number)) {
                    mdialView.cleanDialViewInput();
                }
            }
        });
        
```

## 其他

更多请参考demo

## 待完善

1. 样式未做抽取
2. 事件不全
3. 属性设置未xml 直接设置





