insert into users (id, email, full_name, password, user_role)
values (1,
        'test1@mail.com',
        'test name 1',
        '$2a$12$Y58mVCHzeJefFD/OvttguePtTdjmKLtSj1f2lgFUcC90PofynH.uq',
        'USER'),
       (2,
        'test2@mail.com',
        'test name 2',
        '$2a$12$Y58mVCHzeJefFD/OvttguePtTdjmKLtSj1f2lgFUcC90PofynH.uq',
        'USER'),
       (3,
        'test3@mail.com',
        'test name 3',
        '$2a$12$Y58mVCHzeJefFD/OvttguePtTdjmKLtSj1f2lgFUcC90PofynH.uq',
        'USER'),
       (4,
        'admin@mail.com',
        'Admin',
        '$2a$12$Y58mVCHzeJefFD/OvttguePtTdjmKLtSj1f2lgFUcC90PofynH.uq',
        'ADMIN');


insert into courses (id, name, description, price, course_type, duration, lecture_quantity)
values (1,
        'Course 1',
        'desc 1',
        null,
        'FREE',
        48,
        24),
       (2,
        'Course 2',
        'desc 2',
        null,
        'FREE',
        48,
        24),
       (3,
        'Course 3',
        'desc 3',
        null,
        'FREE',
        48,
        24),
       (4,
        'Course 4',
        'desc 4',
        999,
        'PAID',
        48,
        24);

insert into quizzes (id, title)
values (1, 'Quiz 1');

insert into questions (id, question, quiz_id)
values (1, 'Question 1', 1),
       (2, 'Question 2', 1),
       (3, 'Question 3', 1);

insert into answers (id, answer, correct, question_id)
values (1, 'Answer 1', true, 1),
       (2, 'Answer 2', false, 1),
       (3, 'Answer 1', true, 2),
       (4, 'Answer 2', false, 2),
       (5, 'Answer 1', true, 3),
       (6, 'Answer 2', false, 3);