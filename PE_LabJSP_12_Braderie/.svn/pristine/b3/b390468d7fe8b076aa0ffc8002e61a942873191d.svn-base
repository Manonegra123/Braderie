DECLARE 
  v_description VARCHAR(25);
  v_marque VARCHAR(25);
  v_prix NUMBER (5,2);
  v_boucle NUMBER := 1000000;
BEGIN
  dbms_output.put_line('INSERTIONS DANS LA TABLE T_ARTICLE de ' || v_boucle || ' artcles');
  
  FOR indice IN 8..v_boucle LOOP
	v_description := 'DES' || indice;
	v_marque := 'MAR' || indice;
	v_prix := 10;
    INSERT INTO T_ARTICLE (IDARTICLE,DESCRIPTION,MARQUE,PRIXUNITAIRE) 
    values (indice, v_description, v_marque, v_prix);
  END LOOP;
END;
  