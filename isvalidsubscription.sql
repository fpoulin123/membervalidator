-- FUNCTION: public.isvalidsubscription(date, integer)

-- DROP FUNCTION IF EXISTS public.isvalidsubscription(date, integer);

CREATE OR REPLACE FUNCTION public.isvalidsubscription(
	subscriptiondate date,
	duration integer)
    RETURNS boolean
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$

DECLARE expirationdate date;
BEGIN
	expirationdate = subscriptiondate + duration;

	RETURN expirationdate > now();

END;
$BODY$;

ALTER FUNCTION public.isvalidsubscription(date, integer)
    OWNER TO postgres;
