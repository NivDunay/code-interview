class Provider
  attr_accessor :name, :score, :specialties, :available_dates

  def parse_details(details)
    @name = details['name']
    @score = details['score']
    @specialties = details['specialties'].map do |specialty|
      ProviderSpecialty.new(specialty)
    end
    @available_dates = details['availableDates'].map do |available_date|
      Meeting.new(available_date['from'], available_date['to'])
    end
  end

  def fulfilled_requirements?(min_score, specialty, date)
    provider_meets_score?(min_score) && provider_meets_specialty?(specialty) && provider_meets_date?(date)
  end

  def is_available?(name, date)
    provider_meets_name?(name) && provider_meets_date?(date)
  end

  private

  def provider_meets_name?(name)
    @name.downcase == name
  end

  def provider_meets_score?(min_score)
    @score >= min_score
  end

  def provider_meets_specialty?(required_specialty)
    @specialties.any? do |specialty|
      specialty.meet_requirements?(required_specialty)
    end
  end

  def provider_meets_date?(required_date)
    @available_dates.any? do |available_date|
      available_date.meet_requirements?(required_date)
    end
  end
end
