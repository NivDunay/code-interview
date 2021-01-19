class AppointmentScheduler
  class << self
    attr_accessor :providers_details

    def search_for_provider(min_score, specialty, date)
      providers = get_providers_details
      providers.select do |provider|
        provider.fulfilled_requirements?(min_score, specialty, date)
      end
    end

    def set_appointment(name, date)
      providers = get_providers_details
      selected_providers = providers.select do |provider_metadata|
        provider_metadata.is_available?(name, date)
      end

      selected_providers.one?
    end

    private

    def get_providers_details
      @providers_details = JsonProvider.get_providers_details
    end
  end
end
