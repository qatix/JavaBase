# Optional 类的链式方法

## 原写法
在 Java 8 之前，任何访问对象方法或属性的调用都可能导致 NullPointerException：
```
String isocode = user.getAddress().getCountry().getIsocode().toUpperCase();
```
在这个小示例中，如果我们需要确保不触发异常，就得在访问每一个值之前对其进行明确地检查：
```
if (user != null) {
    Address address = user.getAddress();
    if (address != null) {
        Country country = address.getCountry();
        if (country != null) {
            String isocode = country.getIsocode();
            if (isocode != null) {
                isocode = isocode.toUpperCase();
            }
        }
    }
}
```

## optional 重写
为了更充分的使用 Optional，你可以链接组合其大部分方法，因为它们都返回相同类似的对象。

我们使用 Optional  重写最早介绍的示例。

首先，重构类，使其 getter 方法返回 Optional 引用：

public class User {
    private Address address;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    // ...
}
public class Address {
    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }

    // ...
}
上面的嵌套结构可以用下面的图来表示：

optional nested

现在可以删除 null 检查，替换为 Optional 的方法：

@Test
public void whenChaining_thenOk() {
    User user = new User("anna@gmail.com", "1234");

    String result = Optional.ofNullable(user)
      .flatMap(u -> u.getAddress())
      .flatMap(a -> a.getCountry())
      .map(c -> c.getIsocode())
      .orElse("default");

    assertEquals(result, "default");
}
上面的代码可以通过方法引用进一步缩减：
```
String result = Optional.ofNullable(user)
  .flatMap(User::getAddress)
  .flatMap(Address::getCountry)
  .map(Country::getIsocode)
  .orElse("default");
```
    结果现在的代码看起来比之前采用条件分支的冗长代码简洁多了。