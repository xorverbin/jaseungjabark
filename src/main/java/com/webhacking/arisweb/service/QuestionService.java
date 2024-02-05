package com.webhacking.arisweb.service;

import java.util.List;
import java.time.LocalDateTime;
import com.webhacking.arisweb.entity.Question;
import com.webhacking.arisweb.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.webhacking.arisweb.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import org.springframework.data.domain.Sort;
import lombok.RequiredArgsConstructor;
import com.webhacking.arisweb.entity.SiteUser;
@RequiredArgsConstructor //생성자 방식으로 주입 가능하게 만들어줌
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;


    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }


    public void create(String subject, String content,SiteUser user) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.questionRepository.save(q);
    }
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }
    public void delete(Question question) {
        this.questionRepository.delete(question);
    }
}
