<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>




<%-- Sitemesh là một framework được sử dụng để áp dụng các layout (decorators) cho các trang web. Dưới đây là nguyên lý hoạt động cơ bản khi bạn áp dụng Sitemesh decorator:

Filter Sitemesh:

1. Trước hết, bạn phải đăng ký Filter Sitemesh trong tệp web.xml của ứng dụng web của bạn. Filter này là com.opensymphony.sitemesh.webapp.SiteMeshFilter.
Cấu hình Decorators:

2. Bạn cần cấu hình Sitemesh để xác định các decorators (bố cục trang) và áp dụng chúng cho các URL cụ thể. Cấu hình này thường được thực hiện trong tệp decorators.xml hoặc tệp cấu hình tương tự.
Trang JSP và Thẻ Taglib:

3. Trong mỗi trang JSP mà bạn muốn áp dụng decorator, bạn cần đăng ký thẻ taglib Sitemesh: <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>.
Sử dụng thẻ <decorator:body/> để xác định vị trí nơi nội dung của trang sẽ được chèn vào decorator.
Áp Dụng Decorator:

Filter Sitemesh sẽ quét qua mỗi trang tương ứng với URL và xác định decorator nếu có.
Nội dung của trang sẽ được chèn vào vị trí đã định nghĩa bởi thẻ <decorator:body/>.
Hiển Thị Kết Quả:

Trang đã được trang trí bằng decorator sẽ được hiển thị cho người dùng.
Ví dụ cấu hình decorator trong tệp decorators.xml:

<decorators defaultdir="/WEB-INF/decorators">
    <decorator name="default" page="default.jsp">
        <pattern>*</pattern>
    </decorator>
    <decorator name="admin" page="admin.jsp">
        <pattern>/admin/*</pattern>
    </decorator>
</decorators>
Trong ví dụ này, có hai decorators: "default" và "admin". Decorator "default" sẽ áp dụng cho mọi URL, trong khi decorator "admin" chỉ áp dụng cho các URL bắt đầu bằng "/admin/". Khi một trang được yêu cầu, Sitemesh sẽ áp dụng decorator phù hợp và chèn nội dung của trang vào vị trí đã xác định bởi thẻ <decorator:body/>. --%>