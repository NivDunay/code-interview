class ProviderSpecialty
  attr_accessor :name

  def initialize(name)
    @name = name
  end

  def meet_requirements?(specialty)
    @name.downcase == specialty
  end
end
