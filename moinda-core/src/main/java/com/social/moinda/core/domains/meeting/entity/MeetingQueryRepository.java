package com.social.moinda.core.domains.meeting.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.social.moinda.core.domains.meeting.dto.MeetingDetails;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.social.moinda.core.domains.group.entity.QGroup.group;
import static com.social.moinda.core.domains.meetingmember.entity.QMeetingMember.meetingMember;

/**
 * TODO:
 *  22-07-28 15:10
 *  Meeting Querydsl 작성 도중 Meeting - Member 연관관계 변경 필요.
 */
@Repository
public class MeetingQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    private QMeeting meeting;

    public MeetingQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Meeting.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.meeting = QMeeting.meeting;
    }

    public Optional<MeetingDetails> findMeeting(Long meetingId) {
        Meeting entity = jpaQueryFactory.selectFrom(meeting).distinct()
                .leftJoin(meeting.meetingMember, meetingMember).fetchJoin()
                .leftJoin(meeting.group, group).fetchJoin()
                .where(meeting.id.eq(meetingId))
                .fetchFirst();

        MeetingDetails meetingDetails = entity.bindToMeetingDetails();

        return Optional.ofNullable(meetingDetails);
    }
}