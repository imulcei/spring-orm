INSERT INTO public."bank_advisor" (id, hiring_date, specialty)
SELECT id, CURRENT_DATE, (ARRAY['Assurance','Placement','Prêt immobilier','Crédit consommation'])[floor(random()*4+1)]
FROM public.person
OFFSET 30
LIMIT 10;

INSERT INTO public."insurance" (name) VALUES
('Assurance'),
('Placement'),
('Prêt immobilier'),
('Crédit consommation');

INSERT INTO public."client_insurance" (id_client, id_insurance)
SELECT c.id, i.id
FROM public."client" c
JOIN (
    SELECT id FROM public."insurance" ORDER BY RANDOM() LIMIT 1
) i ON TRUE
ORDER BY RANDOM()
LIMIT 15;

INSERT INTO public."client_bank_advisor" (id_client, id_advisor)
SELECT c.id, a.id
FROM public."client" c
JOIN (
    SELECT id FROM public."bank_advisor" ORDER BY RANDOM() LIMIT 1
) a ON TRUE
ORDER BY RANDOM()
LIMIT 20;
