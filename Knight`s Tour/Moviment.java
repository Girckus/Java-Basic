public class Moviment2 implements Comparable<Moviment2>
{
	private Integer acessbilityRange;
	private Integer movimentNumber;
	private Integer minAccessbility;

	public static final Integer INACCESSIBLE = 10;

	public Moviment2(Integer acessbilityRange, Integer movimentNumber, Integer minAccessbility)
	{
		super();
		this.acessbilityRange = acessbilityRange;
		this.movimentNumber = movimentNumber;
		this.minAccessbility = minAccessbility;
	}

	public Integer getMinAccessbility()
	{
		return minAccessbility;
	}

	public void setMinAccessbility(Integer minAccessbility)
	{
		this.minAccessbility = minAccessbility;
	}

	public Integer getAcessbilityRange()
	{
		return acessbilityRange;
	}

	public void setAcessbilityRange(Integer acessbilityRange)
	{
		this.acessbilityRange = acessbilityRange;
	}

	public Integer getMovimentNumber()
	{
		return movimentNumber;
	}

	public void setMovimentNumber(Integer movimentNumber)
	{
		this.movimentNumber = movimentNumber;
	}

	@Override
	public int compareTo(Moviment2 inter)
	{
		if (this.acessbilityRange.compareTo(inter.getAcessbilityRange()) == 0)
		{
			return this.minAccessbility.compareTo(inter.getMinAccessbility());
		}

		return this.acessbilityRange.compareTo(inter.getAcessbilityRange());
	}
}
