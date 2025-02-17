
SELECT
	S_NAME,
	S_ADDRESS
FROM
	SUPPLIER,
	NATION
WHERE
	S_SUPPKEY IN (
		SELECT
			PS_SUPPKEY
		FROM
			PARTSUPP
		WHERE
			PS_PARTKEY IN (
				SELECT
					P_PARTKEY
				FROM
					PART
				WHERE
					P_NAME LIKE 'FOREST%'
			)
			AND PS_AVAILQTY < (
				SELECT
					0.5 * SUM(L_QUANTITY)
				FROM
					LINEITEM
				WHERE
					L_PARTKEY = PARTSUPP.PS_PARTKEY
					AND L_SUPPKEY = PARTSUPP.PS_SUPPKEY
					AND L_EXTENDEDPRICE :varies
					
			)
	)
	AND S_NATIONKEY = N_NATIONKEY
	AND S_ACCTBAL :varies
	AND N_NAME = 'AMERICA'
ORDER BY
	S_NAME

