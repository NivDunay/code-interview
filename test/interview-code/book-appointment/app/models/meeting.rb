class Meeting
  attr_accessor :from, :to

  def initialize(from, to)
    @from = from.to_i
    @to = to.to_i
  end

  def meet_requirements?(date)
    @from <= date && @to >= date
  end
end
