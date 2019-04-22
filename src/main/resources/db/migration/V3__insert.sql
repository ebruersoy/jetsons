INSERT INTO public.emlakci (id, name, vitrin_hakki) VALUES (1, 'Ilk Emlakçı', 0);

INSERT INTO public.ofis (id, name, emlakci_id) VALUES (1, 'Birinci Ofis', 1);
INSERT INTO public.ofis (id, name, emlakci_id) VALUES (2, 'İkinci Ofis', 1);

INSERT INTO public.danisman (id, name, ofis_id) VALUES (1, 'Danışman 1 Birinci Ofis', 1);
INSERT INTO public.danisman (id, name, ofis_id) VALUES (2, 'Danışman 2 Birinci Ofis', 1);
INSERT INTO public.danisman (id, name, ofis_id) VALUES (3, 'Danışman 3 Birinci Ofis', 1);
INSERT INTO public.danisman (id, name, ofis_id) VALUES (4, 'Danışman 1 İkinci Ofis', 2);
INSERT INTO public.danisman (id, name, ofis_id) VALUES (5, 'Danışman 2 İkinci Ofis', 2);
INSERT INTO public.danisman (id, name, ofis_id) VALUES (6, 'Danışman 3 İkinci Ofis', 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (1, 'ilan 1', false, now(), null, 1, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (2, 'ilan 2', false, now(), null, 2, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (3, 'ilan 3', false, now(), null, 3, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (4, 'ilan 4', false, now(), null, 4, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (5, 'ilan 5', false, now(), null, 5, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (6, 'ilan 6', false, now(), null, 6, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (7, 'ilan 7', false, now(), null, 1, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (8, 'ilan 8', false, now(), null, 2, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (9, 'ilan 9', false, now(), null, 3, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (10, 'ilan 10', false, now(), null, 4, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (11, 'ilan 11', false, now(), null, 5, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (12, 'ilan 12', false, now(), null, 6, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (13, 'ilan 13', false, now(), null, 1, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (14, 'ilan 14', false, now(), null, 2, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (15, 'ilan 15', false, now(), null, 3, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (16, 'ilan 16', false, now(), null, 4, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (17, 'ilan 17', false, now(), null, 5, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (18, 'ilan 18', false, now(), null, 6, 2);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (19, 'ilan 19', false, now(), null, 1, 1);

INSERT INTO public.ilan (id, detail, vitrin_hakki, create_date, update_date, danisman_id, ofis_id)
VALUES (20, 'ilan 20', false, now(), null, 2, 1);
