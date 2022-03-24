package GaBom.Bom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor //Jpa를 위한 기본생성자 생성
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //sequence가 빠르지만, 조금 관리하기 복잡함. 그래서 IDENTITY 자주씀
    //내부에서 db종류에따라서 알아서 설정됨.
    private Long board_id;
    @NotNull //체크안한거
    @Size(min=2, max=30,message = "제목을 2~30으로 맞추도록.")
    private String title;
    private String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;

    private Long price;

    @ManyToOne(fetch=FetchType.LAZY)
    //Board -> User로 참조할때 더 긁지 않기위해서 LAZY
    @JoinColumn(name="user_id")
    private User user;


    public Board(Long board_id, String title, String comment, String startDateString, String endDateString, Long price) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startDateString);
        Date endDate = formatter.parse(endDateString);


        this.board_id = board_id;
        this.title = title;
        this.comment = comment;
        this.startdate = startDate;
        this.enddate = endDate;
        this.price = price;

    }
}
