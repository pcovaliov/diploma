DELETE FROM comments;
DELETE FROM likes;
DELETE FROM status;
DELETE FROM task;
DELETE FROM followers;
DELETE FROM users;

--

INSERT INTO users
  (username, password, first_name, last_name, email)
VALUES
  ('DGULCO', 'dgulco', 'Dmitrii', 'Gulco', 'dgulco@endava.com'),
  ('ACUSNIR', 'ACUSNIR', 'Alexandr', 'Cusnir', 'pcovaliov@endava.com'),
  ('MBIZALIUC', 'MBIZALIUC', 'Mila', 'Bezaliuc', 'pcovaliov@endava.com'),
  ('IPANASENKO', '123456','Iunona', 'Panasenko','panasenko@gmail.com'),
  ('VCRACIUN', '123456', 'Victor', 'Craciun', 'craciun@gmail.com'),
  ('ELAUR', '123456','Edik', 'Laur','edik@endava.com'),
  ('pcovaliov', 'pcovaliov', 'pcovaliov', 'pcovaliov', 'pcovaliov@endava.com'),
  ('ISANDUL', '123456','Ion', 'Sandul','isandul@endava.com'),
  ('SREULET', '123456', 'Serghei', 'Reulet', 'sreulet@endava.com'),
  ('MUSTIMOV', '123456','Maxim', 'Ustimov','mustimov@endava.com');

INSERT INTO task
  (user_id, text, postdatetime)
VALUES
  (1, 'Hi, people. Nice to see you there!', now()),
  (2, 'Awesome weather outside! I ENJOY IT!', now()),
  (3, 'Wowwwww 5 years 5 boys what an amazing journey I couldn''t thank you all ever enough and thank you Louis Niall Harry and zayn for everything', now()),
  (4, 'applied for xfactor,hope it all wrks out', now()),
  (1, 'First thing I saw opening FB today was a meme of a cat praying, with directions to ''like'' to be blessed. And that''s exactly why I''m here.', now()),
  (5, 'The hardest part of Twitter? Trying to figure out what you''re actually addicted to', now()),
  (6, 'Always cultivate grace.', now()),
  (5, 'I''m one nap away from completing one full night''s sleep for the year.', now()),
  (1, 'Somebody knocked at my door while I was on Twitter. I couldn''t answer it.
They left a note.
It was opportunity.
Whew. That was close.', now()),
  (2, 'She wears short skirts
I eat pizza
She''s cheer captain
And I''m still eating pizza', now()),
  (5, 'I want the job where you push scared skydivers out of a plane.', now()),
  (4, 'No matter how long you have traveled in the wrong direction, you can always change course', now()),
  (3, 'WOW! What an epic opening day yesterday, we have literally never seen anything like it...', now()),
  (5, 'Volvamos de @DulceMaria ya tiene más de 1.300.000 views. Entren a su canal oficial y véanlo cuántas veces quieran', now()),
  (1, 'I wish us all the best of luck in navigating this devastatingly divisive climate.', now()),
  (3, 'The Celtics scored 8 points in the 1st quarter - the fewest they''ve scored in the first in over 4 decades.', now()),
  (2, 'Es un honor recibir este @Premios_Ondas y quiero dedicárselo a todos aquellos que han hecho posible que mi carrera sea tan importante.', now()),
  (4, 'I know some of u come to my page looking for words of encouragement and I''m sorry I failed u this time. All I can say: stay angry, send love', now()),
  (2, 'Echando de menos uno de mis deportes favoritos.
Para cuando una inmersión juntos?', now()),
  (1, 'So excited to finally announce EPIC 5.0!
This will be the biggest and most advanced EPIC by far!
Pre-sale Signup:
http://creamfields.com/steelyard ', now()),
  (6, 'I''ve been hooked on Skyrim SE this week. My PS4 Pro arrives tomorrow...then the real COD grind begins. I wonder if it''ll be much different.', now()),
  (2, 'Been sitting here for like 1 hour just thinking where im accually standing as a gamer and as a person i couldn''t be more happy and satisfied', now()),
  (1, 'Good morning twitterers. I hope everyone has a marvelous day.', now()),
  (5, 'Let me tell you ''bout the story from Huraches to Versaces. My partner pulled up, I had to chef it up, hibachi. I gotta go to work.', now()),
  (3, 'If you''re upset it''s probably just best to stay off social media right now. It''s just as easy to be stressed as it is to add to it', now()),
  (2, 'INCREIBLE nuestra unión y nuestro amor! Gracias por acompañarm.... http://tmi.me/1fgndF ', now()),
  (1, 'Today I become a fashionista! What do you think of my cool new look?', now()),
  (6, 'Volatile times. Holding my breath.', now()),
  (7, 'Happen to be in NYC election night... The energy in the city is absolutely insane ', now());




