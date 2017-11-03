package com.spring.Service;

import com.spring.Reply.dao.ReplyDao;
import com.spring.Reply.dto.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    Calendar calendar;
    public List<ReplyVO> list(String boardnumber) {
        return replyDao.list(boardnumber);
    }

    public void create(ReplyVO vo) {
        vo.setRno(getReplyNumber());
        replyDao.create(vo);
    }

    public void update(ReplyVO vo) {
        replyDao.update(vo);
    }

    public void delete(String rno) {
        replyDao.delete(rno);
    }

    public String getReplyNumber(){
        calendar= Calendar.getInstance();
        String boarnumber= String.valueOf(calendar.get(Calendar.YEAR))+ String.valueOf(calendar.get(Calendar.MONTH))+
                String.valueOf(calendar.get(Calendar.DATE))+String.valueOf(calendar.get(Calendar.MILLISECOND));
        return boarnumber;
    }

    public int count(String boardnumber){
        System.out.println(boardnumber+"#############################reply service count");
        return replyDao.count(boardnumber);
    }
}
