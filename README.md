# school_holiday_statistics
一个简单的假期管理系统,统计学生假期去向信息

### 简单介绍
* 框架篇
    * Spring boot
    * bootstrap
    * vue.js
* 功能篇
    * 绑定邮箱并验证邮箱重置密码
    * 修改个人信息
    * 学生提交假期去向信息并可查看历史提交记录
    * 管理员可查看所有提交记录并导出Excel表格

### 如何配置？
* 需要在配置文件中配置数据库，新建数据库表自动生成。
* 需要配置导出excel信息，将HolidayTemplate.xls拷贝到硬盘目录并在配置中写明路径。
* 若要自定义表格请自行修改[service/DownExcelServive/ExcelTemplate](src/main/java/com/holidaystatistics/service/DownExcelService/ExcelTemplate.java)
* 初始数据（学生信息、管理员信息）需手动导入数据库(密码为Md5加密)。


### 其他
* 关于作者
    * nitmali
    * [邮件:me@nitmali.com](me@nitmali.com)
* 项目部署
    * 部署地址： [holiday.nitmali.com](holiday.nitmali.com)
    

