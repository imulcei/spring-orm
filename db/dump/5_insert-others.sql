INSERT INTO public."bank_advisor" (id, hiring_date, specialty)
SELECT id, CURRENT_DATE, (ARRAY['ASSURANCE'::specialty, 'PLACEMENT'::specialty, 'PRET_IMMO'::specialty, 'CREDIT_CONSO'::specialty])[floor(random()*4+1)]
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

-- DROP PROCEDURE public.create_client_bankadvisor();

CREATE OR REPLACE
PROCEDURE public.create_client_bankadvisor()
	LANGUAGE plpgsql
AS $$
	DECLARE
		random_id_bankadvisor UUID;
		cl RECORD;
	BEGIN
	FOR cl IN
		SELECT
			*
		FROM
			public."client"  
	LOOP
		SELECT id 
		into random_id_bankadvisor
		FROM public."bank_advisor" ORDER BY RANDOM() LIMIT 1;
		IF cl.id != random_id_bankadvisor THEN
			INSERT INTO public."client_bank_advisor" (id_client, id_advisor)
			VALUES (cl.id, random_id_bankadvisor);
		END IF;
	END LOOP;
END;
$$;

CALL public.create_client_bankadvisor();
