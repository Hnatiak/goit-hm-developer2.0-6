SELECT CONCAT('Project ', p.id) AS "name",
       DATEDIFF('DAY', p.start_date, p.finish_date) AS "duration"
FROM project p
WHERE DATEDIFF('DAY', p.start_date, p.finish_date) = (
    SELECT MAX(DATEDIFF('DAY', start_date, finish_date)) FROM project
);