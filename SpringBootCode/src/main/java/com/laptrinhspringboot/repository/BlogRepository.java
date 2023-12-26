package com.laptrinhspringboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laptrinhspringboot.model.Blog;

// @Repository -> ko cần vì ta extend JpaRepository là spring boot tự hiểu
public interface BlogRepository extends JpaRepository<Blog, Integer>{

	//  JPQL
	@Query("SELECT b FROM Blog b WHERE b.author = :au AND b.topic = :to")  // from Blog - đây là tên của class Blog
	Blog findByAuthorAndTopicBlog(@Param("au") String author, @Param("to") String topic);
	
	@Query("SELECT b FROM Blog b WHERE b.author LIKE '%' || :au  || '%'")
	Page<Blog> findBlogPagingByAuthorName(@Param("au") String author, Pageable pageable);
	
	@Query("SELECT b FROM Blog b INNER JOIN b.eCommerce e WHERE b.author LIKE CONCAT('%', :au, '%') AND e.id = :eID")
	Page<Blog> findAllBlogPagingByEcomAndAuthorName(@Param("eID") int ecomId, @Param("au") String author, Pageable pageable);
	
	
	//@Query("SELECT b FROM Blog b INNER JOIN ECommerce e ON b.ecommerce_id = e.id WHERE ecommerce_id = :eID")
	@Query("SELECT b FROM Blog b INNER JOIN b.eCommerce e WHERE e.id = :eID")  
	Page<Blog> findAllBlogPagingByECommerce(@Param("eID") int id, Pageable pageable);
	// eCommerce trong câu query là trường ta đặt quan hệ bên class model Blog
	//e là alias cho đối tượng ECommerce được tham chiếu bởi b.eCommerce.
	// Nếu ta để spring.jpa.show-sql=true bên application.properties thì sẽ nhìn thấy được hibernate thực hiện câu query
	// ở dưới console:
	// -> Hibernate: select b1_0.id,b1_0.author,b1_0.ecommerce_id,b1_0.topic from blog b1_0 join ecommerce ec1_0 on ec1_0.id=b1_0.ecommerce_id where ec1_0.id=? limit ?,?
	
	
	/*
	 * Trong trường hợp này, Pageable sẽ tự động quản lý phân trang cho bạn. Khi sử
	 * dụng phương thức này, Spring Data JPA sẽ tự động xử lý OFFSET và LIMIT dựa
	 * trên thông tin trong đối tượng Pageable.
	 */
	
	/*
	 * Khi bạn sử dụng JPQL (Java Persistence Query Language), bạn thường không cần
	 * sử dụng ON như trong SQL. Trong JPQL, quan hệ giữa các Entity được xác định
	 * thông qua mối quan hệ giữa chúng trong model của bạn, thay vì thông qua điều
	 * kiện ON như trong SQL.
	 */
	
	
	
	// SQL
	/*
	 * @Query(value = "SELECT * FROM blog WHERE author = :au", nativeQuery = true)  // form blog - đây là tên bảng trong database
	 * List<Blog> findBlogsByAuthor(@Param("au") String author);
	 * 
	 * Lưu ý rằng khi sử dụng truy vấn SQL native, bạn phải sử dụng tên của bảng và
	 * các trường trong cơ sở dữ liệu, không phải tên của Entity và các thuộc tính
	 * của nó. Mặc dù có những lợi ích về hiệu suất khi sử dụng truy vấn SQL native,
	 * nhưng nó có thể làm giảm tính di động và tái sử dụng của mã nguồn. -> khi
	 * thay csdl với tên bảng khác là toang
	 */
}
