SELECT
	P_BRAND,
	P_TYPE,
	P_SIZE,
	COUNT(DISTINCT PS_SUPPKEY) AS SUPPLIER_CNT
FROM
	PARTSUPP,
	PART
WHERE
	P_PARTKEY = PS_PARTKEY
	AND P_RETAILPRICE :varies
	AND PS_SUPPKEY IN (
		SELECT
			S_SUPPKEY
		FROM
			SUPPLIER
		WHERE
			S_ACCTBAL :varies
	)
GROUP BY
	P_BRAND,
	P_TYPE,
	P_SIZE
ORDER BY
	SUPPLIER_CNT DESC,
	P_BRAND,
	P_TYPE,
	P_SIZE

