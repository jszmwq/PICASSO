select
	p_brand,
	p_type,
	p_retailprice,
	count(distinct ps_suppkey) as supplier_cnt
from
	partsupp,
	part
where
	p_partkey = ps_partkey
	and p_retailprice :varies
	and ps_suppkey in (
		select
			s_suppkey
		from
			supplier
		where
			s_acctbal :varies
	)
group by
	p_brand,
	p_type,
	p_retailprice
order by
	supplier_cnt desc,
	p_brand,
	p_type,
	p_retailprice

