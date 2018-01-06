-- INSERT INTO ROLES (ROLE_ID, NAME)
--   (SELECT
--      1, 'ROLE_ADMIN'
--    WHERE NOT EXISTS(
--        SELECT 1
--        FROM ROLES
--        WHERE ROLE_ID = 1 AND NAME = 'ROLE_ADMIN')
--   );

INSERT INTO ROLES (ROLE_ID, NAME)
VALUES (1, 'ADMIN') ON CONFLICT(ROLE_ID) DO NOTHING;

INSERT INTO USERS (USER_ID ,USERNAME, PASSWORD, ENABLED, ROLE_ID)
VALUES (1, 'admin', '123', TRUE, 1) ON CONFLICT(USER_ID) DO NOTHING;

INSERT INTO CAR_BRANDS (CAR_BRAND_ID, NAME)
VALUES (1, 'Audi'), (2, 'Nissan'), (3, 'Kia'), (4, 'Lada')
ON CONFLICT(CAR_BRAND_ID) DO NOTHING;

INSERT INTO CAR_MODELS (CAR_MODEL_ID, NAME)
VALUES (1, 'A4'), (2, 'A6'),
  (3, 'Juke'), (4, 'Qashqai'),
  (5, 'Rio'), (6, 'Sportage'),
  (7, 'Granta'), (8, 'Vesta')
ON CONFLICT(CAR_MODEL_ID) DO NOTHING;

INSERT INTO ADVERTISEMENTS (ADVERTISEMENT_ID, DESCRIPTION, CAR_BRAND_ID, CAR_MODEL_ID)
VALUES (1, 'my 1 advert', 1, 2),
  (2, 'my 2 advert', 2, 3),
  (3, 'my 3 advert', 3, 5),
  (4, 'my 4 advert', 4, 8)
ON CONFLICT(ADVERTISEMENT_ID) DO NOTHING;;